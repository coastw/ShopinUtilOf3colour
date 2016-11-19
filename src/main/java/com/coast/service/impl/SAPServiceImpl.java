package com.coast.service.impl;

import com.coast.controler.Controler;
import com.coast.model.Discount;
import com.coast.model.Product;
import com.coast.model.ResultMSG;
import com.coast.service.SAPService;
import com.coast.util.DiscountUtil;
import com.coast.util.ProductToSAPUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Coast
 */
public class SAPServiceImpl implements SAPService {

    private final String NEXT_LINE = System.getProperty("line.separator");

    @Override
    public void generateSingleBrandSAP(List<Product> products, List<Discount> discounts, File destFile, ResultMSG resultMSG) {

        int sum = 0;
        InputStream is = null;
        OutputStream os = null;
        Workbook wb = null;
        try {

            //choose sap template
            if (products.size() < 300) {
                is = Controler.class.getResourceAsStream("/saptemplate/sap300.xlsx");
            } else if (products.size() < 1000) {
                is = Controler.class.getResourceAsStream("/saptemplate/sap1000.xlsx");
            } else {
                is = Controler.class.getResourceAsStream("/saptemplate/sapinfinity.xlsx");
            }

            wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);

            //供应商名称：
            Cell supplierCell = sheet.getRow(1).getCell(1);
            supplierCell.setCellValue("卓尚服饰（杭州）有限公司");
            //品牌名称：
//            Cell brandCell = sheet.getRow(2).getCell(1);
//            brandCell.setCellValue("sonca桑卡");
            String brand = null;
            Iterator<Product> iter = products.iterator();

            int row = 4;
            while (iter.hasNext()) {
                Product product = iter.next();
                ProductToSAPUtil sapUtil = new ProductToSAPUtil(product);
                if (brand == null) {
                    brand = sapUtil.getBrand();
                    Cell brandCell = sheet.getRow(2).getCell(1);
                    brandCell.setCellValue(brand);
                }
                //条码 
                Cell fullSnCell = sheet.getRow(row).getCell(0);
                fullSnCell.setCellValue(product.getFullSn());
                //款号
                Cell snCell = sheet.getRow(row).getCell(1);
                snCell.setCellValue(product.getSnCode());
                //单位
                Cell unitCell = sheet.getRow(row).getCell(2);
                unitCell.setCellValue(sapUtil.getUnit());
                //颜色信息
                Cell colorCell = sheet.getRow(row).getCell(3);
                colorCell.setCellValue(product.getColorCode());
                //色系
                Cell colorTypeCell = sheet.getRow(row).getCell(4);
                colorTypeCell.setCellValue(sapUtil.getColorType());
                //1级品类名称
                Cell firstTypeCell = sheet.getRow(row).getCell(5);
                firstTypeCell.setCellValue(sapUtil.getFirstType());
                //2级品类名称
                Cell secondTypeCell = sheet.getRow(row).getCell(6);
                secondTypeCell.setCellValue(sapUtil.getSecondType());
                //3极品类名称
                Cell thirdTypeCell = sheet.getRow(row).getCell(7);
                thirdTypeCell.setCellValue(sapUtil.getThirdType());
                //国际尺码
                Cell internationalSizeCell = sheet.getRow(row).getCell(11);
                //new
                internationalSizeCell.setCellValue(sapUtil.getInternationalSize());
                //old
//                internationalSizeCell.setCellValue(product.getSize() + "(" + sapUtil.getInternationalSize() + ")");
                //企业尺码
//                Cell brandSizeCell = sheet.getRow(row).getCell(11);
//                brandSizeCell.setCellValue(product.getSize());
                //适合季节
                Cell fitSeasonCell = sheet.getRow(row).getCell(14);
                fitSeasonCell.setCellValue(sapUtil.getFitSeason());
                //年份
                Cell yearCell = sheet.getRow(row).getCell(15);
                yearCell.setCellValue(sapUtil.getYear());
                //attribute
                Cell attributeCell = sheet.getRow(row).getCell(16);
                attributeCell.setCellValue("无");
                //price
                //new
                Cell orgPricecell = sheet.getRow(row).getCell(20);
                if (orgPricecell == null) {
                    orgPricecell = sheet.getRow(row).createCell(20);
                }
                orgPricecell.setCellValue(product.getOrgPrice());

                //21 now price
                //设置百分比格式--使用自定义的格式
                //cell=row.createCell(3);
                //cell.setCellValue(0.123456789);
                //style=workbook.createCellStyle();
                //style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
                //cell.setCellStyle(style);
                //22 discount
                String persent = new DiscountUtil().getPercent(product.getSnCode(), discounts);
                Cell discountCell = sheet.getRow(row).createCell(22);
                //% -> double
                NumberFormat percentNumberFormat = NumberFormat.getPercentInstance();
                Number number = percentNumberFormat.parse(persent);
                double doublePercent = number.doubleValue();
                discountCell.setCellValue(doublePercent);

                //21 now price
                Cell nowPriceCell = sheet.getRow(row).getCell(21);
                double nowPrice = Math.floor(Integer.parseInt(product.getOrgPrice()) * doublePercent);
                nowPriceCell.setCellValue(nowPrice);

                //23
                Cell calculateCell = sheet.getRow(row).createCell(23, Cell.CELL_TYPE_FORMULA);
                String formula = "INT(U" + (row + 1) + "*W" + (row + 1) + ")";
                calculateCell.setCellFormula(formula);

                //下一行
                sum += product.getAmount();
                row++;
            }
            //Formula
            wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            os = new FileOutputStream(destFile);
            wb.write(os);
            os.flush();

            resultMSG.appendWriteMessage("SAP写入完成,共:" + sum + "件");
        } catch (Exception e) {
            resultMSG.appendErrorMessage("SAP写入出错,共:" + sum + "件,错误:" + e.getLocalizedMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    resultMSG.appendErrorMessage("关闭SAP模板文件发生错误:" + ex.getLocalizedMessage());
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    resultMSG.appendErrorMessage("关闭写入SAP文件发生错误:" + ex.getLocalizedMessage());
                }
            }
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException ex) {
                    resultMSG.appendErrorMessage("关闭创建的Excel文件发生错误:" + ex.getLocalizedMessage());
                }
            }
        }
    }

}
