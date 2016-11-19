package com.coast.service.impl;

import static com.coast.controler.Controler.getRowNum;
import com.coast.model.Discount;
import com.coast.model.Product;
import com.coast.model.ResultMSG;
import com.coast.service.ShopinPurchaseOrderService;
import com.coast.util.DiscountUtil;
import com.coast.util.POIUtil;
import com.coast.util.ProductToSAPUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Coast
 */
public class ShopinPurchaseOrderServiceImpl implements ShopinPurchaseOrderService {

    private final String NEXT_LINE = System.getProperty("line.separator");

    @Override
    public void generatePurchaseOrder(File drpSourceFile, File destDir, File s_shopinPurchaseOrderTemplateFile, File k_shopinPurchaseOrderTemplateFile, ResultMSG resultMSG) {
        /*
        步骤:
        1.读取DRP导出的Excel,将不同品牌的款分别放在不同的List<Product>中(Map中包含2个List).
        2.将第一步中的List生成PurchaseOrder,将没有SAP的款根据品牌放入到新的List中.
        3.将需要生成SAP的List生成上品主数据.
         */

        //1
        Map<String, List<Product>> productsMap = readDRPExcel(drpSourceFile, resultMSG);

        //2
        Map<String, List<Product>> noSAPProdutsMap = writePurchaseOrder(productsMap, drpSourceFile.getName(), destDir, s_shopinPurchaseOrderTemplateFile, k_shopinPurchaseOrderTemplateFile, resultMSG);

        //3
        writeSAP(noSAPProdutsMap, new DiscountUtil().read(), drpSourceFile.getName(), destDir, resultMSG);
    }

    /**
     * 读取DRP导出的Excel,将不同品牌的款分别放在不同的List<Product>中(Map中包含2个List).
     *
     * @param sourceFile
     * @param resultMSG
     * @return
     */
    private Map<String, List<Product>> readDRPExcel(File sourceFile, ResultMSG resultMSG) {
        //键值对：键为款号首字母，值为List
        Map<String, List<Product>> map = new HashMap<>();
        List<Product> sProducts = new ArrayList<>();
        List<Product> kProducts = new ArrayList<>();
        //工具
        POIUtil poiUtil = new POIUtil();
        //计数
        int sSumAmount = 0;
        int kSumAmount = 0;

        try (InputStream is = new FileInputStream(sourceFile); Workbook wb = WorkbookFactory.create(is)) {

            Sheet sheet = wb.getSheetAt(0);
            //根据标题行判断所需列的位置。
            Row titleRow = sheet.getRow(0);
            Map<String, Integer> columePositionMap = getColumePosition(titleRow);
            //获取需要的列的序号
            int snCodeColumnIndex = columePositionMap.get("商品代码");
            int typeColumnIndex = columePositionMap.get("商品名称");
            int colorColumnIndex = columePositionMap.get("颜色");
            int sizeColumnIndex = columePositionMap.get("规格");
            int priceColumnIndex = columePositionMap.get("单价");
            int amountColumnIndex = columePositionMap.get("数量");
            //遍历行
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //跳过标题行
                if (row.getRowNum() == 0) {
                    continue;
                }

                //fullsn
                Cell fullSnCell = row.getCell(snCodeColumnIndex);
                String fullSn = poiUtil.getCellContentToString(fullSnCell);
                int len = fullSn.length();
                String snCode = fullSn.substring(0, len - 3);
                String colorCode = fullSn.substring(len - 3, len - 1);
                String sizeCode = fullSn.substring(len - 1, len);
                //String sizeRegex = convertSizeToRegex(sizeCode);

                //type
                Cell typeCell = row.getCell(typeColumnIndex);
                String type = poiUtil.getCellContentToString(typeCell);

                //color
                Cell colorCell = row.getCell(colorColumnIndex);
                String color = poiUtil.getCellContentToString(colorCell);

                //size
                Cell sizeCell = row.getCell(sizeColumnIndex);
                String size = poiUtil.getCellContentToString(sizeCell);

                //price
                Cell priceCell = row.getCell(priceColumnIndex);
                String orgPrice = poiUtil.getCellContentToString(priceCell);

                //amount
                Cell amountCell = row.getCell(amountColumnIndex);
                int amount = Integer.parseInt(poiUtil.getCellContentToString(amountCell));

                //Porduct
                Product product = new Product(fullSn, snCode, colorCode, sizeCode, type, color, size, orgPrice, amount);
                //放在不同品牌的List中
                if (product.getSnCode().toUpperCase().startsWith("S")) {
                    sProducts.add(product); //三彩
                    sSumAmount += product.getAmount();  //计数
                } else {
                    kProducts.add(product); //桑卡
                    kSumAmount += product.getAmount();  //计数
                }

            }
            map.put("S", sProducts);
            map.put("K", kProducts);
            resultMSG.appendReadMessage("读取DRP中导出的Excel成功，其中S的共" + sSumAmount + "件；K的共" + kSumAmount + "件");
            return map;
        } catch (Exception e) {
            resultMSG.appendErrorMessage("读取DRP中导出的Excel出错！"+e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 根据行判断每个列的名称，并将列名和列的位置存放在Map中
     *
     * @param titleRow
     * @return
     */
    private Map<String, Integer> getColumePosition(Row titleRow) {
        Map<String, Integer> columePositionMap = new HashMap<>();
        POIUtil poiUtil = new POIUtil();
        Iterator<Cell> cellIterator = titleRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            String cellText = poiUtil.getCellContentToString(cell);
            switch (cellText) {
                case "商品代码":
                    columePositionMap.put("商品代码", cell.getColumnIndex());
                    break;
                case "商品名称":
                    columePositionMap.put("商品名称", cell.getColumnIndex());
                    break;
                case "颜色":
                    columePositionMap.put("颜色", cell.getColumnIndex());
                    break;
                case "规格":
                    columePositionMap.put("规格", cell.getColumnIndex());
                    break;
                case "单价":
                    columePositionMap.put("单价", cell.getColumnIndex());
                    break;
                case "数量":
                    columePositionMap.put("数量", cell.getColumnIndex());
                    break;
            }
        }
        return columePositionMap;
    }

    /**
     * 将第一步中的List生成PurchaseOrder,将没有SAP的款根据品牌放入到新的List中
     *
     * @param productsMap
     * @param destDir
     * @param resultMSG
     * @return
     */
    private Map<String, List<Product>> writePurchaseOrder(Map<String, List<Product>> productsMap, String drpSourceFileName, File destDir, File s_shopinPurchaseOrderTemplateFile, File k_shopinPurchaseOrderTemplateFile, ResultMSG resultMSG) {
        Map<String, List<Product>> noSAPProductsMap = new HashMap<>();
        List<Product> sNoSAPProducts = new ArrayList<>();
        List<Product> kNoSAPProducts = new ArrayList<>();

        for (Map.Entry<String, List<Product>> entry : productsMap.entrySet()) {
            String brandPrefix = entry.getKey();
            List<Product> products = entry.getValue();
            if (!products.isEmpty()) {
                if (brandPrefix.equals("S")) {
                    sNoSAPProducts = writeSinglePurchaseOrderToExcel(brandPrefix, products, drpSourceFileName, destDir, s_shopinPurchaseOrderTemplateFile, resultMSG);
                } else {
                    kNoSAPProducts = writeSinglePurchaseOrderToExcel(brandPrefix, products, drpSourceFileName, destDir, k_shopinPurchaseOrderTemplateFile, resultMSG);
                }
            }
        }
        noSAPProductsMap.put("S", sNoSAPProducts);
        noSAPProductsMap.put("K", kNoSAPProducts);
        return noSAPProductsMap;
    }

    /**
     * 真正的将数据写入Excel
     *
     * @param brandString
     * @param products
     * @param drpSourceFileName
     * @param destDir
     * @param shopinPurchaseOrderTemplateFile
     * @param resultMSG
     * @return
     */
    private List<Product> writeSinglePurchaseOrderToExcel(String brandPrefix, List<Product> products,
            String drpSourceFileName, File destDir, File shopinPurchaseOrderTemplateFile, ResultMSG resultMSG) {

        List<Product> noSAProducts = new ArrayList<>();

        String fileName = drpSourceFileName.substring(0, drpSourceFileName.lastIndexOf(".")) + "__" + brandPrefix + "__Order.xls";
        String outputFilePath = destDir.getPath() + File.separator + fileName;

        File outputFile = new File(outputFilePath);

        int sumAmount = 0;
        try (InputStream is = new FileInputStream(shopinPurchaseOrderTemplateFile);
                OutputStream os = new FileOutputStream(outputFile);
                Workbook wb = WorkbookFactory.create(is)) {

            Sheet sheet = wb.getSheetAt(0);
            String brand = null;
            Iterator<Product> iter = products.iterator();
            while (iter.hasNext()) {
                Product product = iter.next();
                ProductToSAPUtil sapUtil = new ProductToSAPUtil(product);

                int thatRowNum = getRowNum(sheet, product.getSnCode(), product.getColorCode(), sapUtil.getInternationalSize());
                if (thatRowNum == 0) {
                    String notFoundMessage = "没有找到对应的SAP！sn=" + product.getSnCode() + " color=" + product.getColorCode() + " size=" + sapUtil.getInternationalSize() + " amount=" + product.getAmount() + "\n";
                    //添加到没有SAP的List
                    noSAProducts.add(product);
                    resultMSG.appendWriteMessage(notFoundMessage);
                } else {
                    //如果有就加1
                    Cell amountCell = sheet.getRow(thatRowNum).getCell(7);
                    if (amountCell != null) {
                        amountCell.setCellValue(amountCell.getNumericCellValue() + product.getAmount());
                    } else {
                        sheet.getRow(thatRowNum).createCell(7).setCellValue(product.getAmount());
                    }
                    sumAmount += product.getAmount();
                }
            }

            wb.write(os);
            os.flush();
            resultMSG.appendWriteMessage("入库订单写入完成,共:" + sumAmount + "件");
            return noSAProducts;
        } catch (Exception e) {
            resultMSG.appendErrorMessage("入库订单写入出错,共:" + sumAmount + "件,错误:" + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 把缺失SAP的数据写入SAP文件
     *
     * @param noSAPProdutsMap
     * @param name
     * @param destDir
     * @param resultMSG
     */
    private void writeSAP(Map<String, List<Product>> noSAPProdutsMap, List<Discount> discounts, String drpSourceFileName, File destDir, ResultMSG resultMSG) {

        SAPServiceImpl sapService = new SAPServiceImpl();
        for (Map.Entry<String, List<Product>> entry : noSAPProdutsMap.entrySet()) {

            String brandPrefix = entry.getKey();
            List<Product> products = entry.getValue();

            if (!products.isEmpty()) {
                String fileName = drpSourceFileName.substring(0, drpSourceFileName.lastIndexOf(".")) + "__" + brandPrefix + "__SAP.xlsx";
                String outputFilePath = destDir.getPath() + File.separator + fileName;

                File outputFile = new File(outputFilePath);

                sapService.generateSingleBrandSAP(products, discounts, outputFile, resultMSG);
            }

        }
    }

}
