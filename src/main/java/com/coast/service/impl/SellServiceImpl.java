package com.coast.service.impl;

import com.coast.model.Product;
import com.coast.model.ResultMSG;
import com.coast.service.SellService;
import com.coast.util.POIUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Coast
 */
public class SellServiceImpl implements SellService {

    @Override
    public List<Product> getProductsFromShopinSellExcel(String filePath, ResultMSG resultMSG) {
        //msg
        int sum = 0;
        int allPrice = 0;
        //开始读
        int row = 1;//第二行开始
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);
        try (InputStream is = new FileInputStream(file); Workbook wb = WorkbookFactory.create(is)) {
            POIUtil poiUtil = new POIUtil();
            Sheet sheet = wb.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            //
//            while(sheet.getRow(row).getCell(0) ==null){
            while (row <= lastRowNum) {
                //sn
                Cell snCell = sheet.getRow(row).getCell(4);
                String snCode = poiUtil.getCellContentToString(snCell);
                //color
                Cell colorCodeCell = sheet.getRow(row).getCell(5);
                String colorCode = poiUtil.getCellContentToString(colorCodeCell);
                //size
                Cell sizeTextCell = sheet.getRow(row).getCell(6);
                String sizeText = poiUtil.getCellContentToString(sizeTextCell);
                //TODO
                String sizeCode = getSizeCode(sizeText);

                //price
                Cell priceCell = sheet.getRow(row).getCell(11);
                String price = poiUtil.getCellContentToString(priceCell);
                //为啥要负要变正? 因为统计总售价的时候-*-变成+。实际上只需要数量为-就ok
                //是不是负, 是负变成正。将退货的销售价格变为正，销售数量保持原有。
                boolean isNagetive = false;
                if (price.matches("^-.*")) {
                    price = price.substring(1);
                    isNagetive = true;
                }

                //amount
                Cell amountCell = sheet.getRow(row).getCell(13);
                String amount = poiUtil.getCellContentToString(amountCell);
                //是不是负, 是正变成负
//                if (isNagetive) {
//                    amount = "-" + amount;
//                }
                //记录数量
                sum += Integer.parseInt(amount);
                double sellPrice = Double.parseDouble(price);
                double sellAmount = Double.parseDouble(amount);
                allPrice += sellPrice * sellAmount;

                //add to list
                Product product = new Product();
                String fullSn = snCode + colorCode + sizeCode;
                product.setFullSn(fullSn);
                product.setAmount(Integer.parseInt(amount));
                product.setSellPrice(price);
                products.add(product);
                row++;
            }

            resultMSG.setReadMessage("读取上品销售Excel完成:共" + sum + "件!总金额:" + allPrice + "元!");
            return products;
        } catch (Exception ex) {
            resultMSG.appendErrorMessage("读取上品销售Excel出错!" + ex.getMessage());
            return null;
        }
    }

    @Override
    public void write(List<Product> products, String outPutFilePath, ResultMSG resultMSG, String shopinFilePath) {
        File shopinFile = new File(shopinFilePath);
        String fileName = shopinFile.getName();
        fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "__销售小票.xls";
        String outputFilePath = outPutFilePath + File.separator + fileName;
        
        File file = new File(outputFilePath);
        if (file.exists()) {
            file.delete();
        }
        try (Workbook wb = new HSSFWorkbook(); FileOutputStream fos = new FileOutputStream(file)) {
            //Workbook wb = new XSSFWorkbook();
            //CreationHelper createHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("销售小票");

            //DRP会读取第一行作为标题行,所以数据从第二行开始写
            Row titileRow = sheet.createRow(0);
            Cell fullSnCell = titileRow.createCell(0);
            fullSnCell.setCellValue("商品代码");
            Cell amountCell = titileRow.createCell(2);
            amountCell.setCellValue("数量");
            Cell sellPriceCell = titileRow.createCell(1);
            sellPriceCell.setCellValue("折后价");
            int rowNum = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowNum);

                Cell cell = row.createCell(0);
                cell.setCellValue(product.getFullSn());

                cell = row.createCell(2);
                cell.setCellValue(product.getAmount());

                cell = row.createCell(1);
                cell.setCellValue(product.getSellPrice());
                
                rowNum++;
            }
            // Write the output to a file
            wb.write(fos);
            resultMSG.setWriteMessage("写入销售小票完成");
        } catch (Exception ex) {
            resultMSG.appendErrorMessage("写入销售小票出错!" + ex.getMessage());
        }
    }

    //类内部用
    private String getSizeCode(String sizeText) {
        String sizeCode = "#";
        String size = sizeText.toUpperCase();
        if (size.matches("^F.*")) {
            sizeCode = "0";
        } else if (size.matches("^S.*") || size.matches("^155.*")||size.matches("^75CM")) {
            sizeCode = "1";
        } else if (size.matches("^M.*") || size.matches("^160.*")||size.matches("^80CM")) {
            sizeCode = "2";
        } else if (size.matches("^L.*") || size.matches("^165.*")||size.matches("^85CM")) {
            sizeCode = "3";
        } else if (size.matches("^XL.*") || size.matches("^170.*")||size.matches("^90CM")) {
            sizeCode = "4";
        } else if (size.matches("^2XL.*") || size.matches("^XXL.*") || size.matches("^175.*")||size.matches("^95CM")) {
            sizeCode = "5";
        } else if (size.matches("^3XL.*") || size.matches("^XXXL.*") || size.matches("^180.*")||size.matches("^100CM")) {
            sizeCode = "6";
        } else if (size.matches("^4XL.*") || size.matches("^185.*")||size.matches("^105CM")) {
            sizeCode = "7";
        } else if (size.matches("^XS.*") || size.matches("^150.*")||size.matches("^70CM")) {
            sizeCode = "8";
        } else if (size.matches("^5XL.*") || size.matches("^190.*")||size.matches("^110CM")) {
            sizeCode = "9";
        }

        return sizeCode;
    }

}//Class End
