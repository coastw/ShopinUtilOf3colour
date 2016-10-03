package com.coast.service.impl;

import com.coast.model.BJDProduct;
import com.coast.model.Discount;
import com.coast.model.ResultMSG;
import com.coast.service.BJDService;
import com.coast.util.DiscountUtil;
import com.coast.util.POIUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Coast
 */
public class BJDServiceImpl implements BJDService {

    @Override
    public List<BJDProduct> readDRPStock(String drpFilePath, ResultMSG resultMSG) {
        //开始读
        int row = 1;//第二行开始
        List<BJDProduct> products = new ArrayList<>();
        File file = new File(drpFilePath);
        try (InputStream is = new FileInputStream(file); Workbook wb = WorkbookFactory.create(is)) {
            POIUtil poiUtil = new POIUtil();
            Sheet sheet = wb.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            //
//            while(sheet.getRow(row).getCell(0) ==null){
            while (row <= lastRowNum) {
                //sn
                Cell snCodeCell = sheet.getRow(row).getCell(2);
                String snCode = poiUtil.getCellContentToString(snCodeCell);

                //type
                Cell typeCell = sheet.getRow(row).getCell(3);
                String type = poiUtil.getCellContentToString(typeCell);

                //price
                Cell priceCell = sheet.getRow(row).getCell(10);
                String price = poiUtil.getCellContentToString(priceCell);

                //year
                Cell yearCell = sheet.getRow(row).getCell(8);
                String year = poiUtil.getCellContentToString(yearCell);

                //season
                Cell seansonCell = sheet.getRow(row).getCell(9);
                String season = poiUtil.getCellContentToString(seansonCell);

                //add to list
                BJDProduct product = new BJDProduct();
                product.setSnCode(snCode);
                product.setType(type);
                product.setYear(year);
                product.setSeason(season);
                product.setPrice(Double.parseDouble(price));
                //排除重复
                if (!products.contains(product)) {
                    products.add(product);
                }
                row++;
            }

            resultMSG.setReadMessage("读取DRP库存完成:共" + row + "行!");
            return products;
        } catch (Exception ex) {
            resultMSG.setErrorMessage("读取DRP库存出错!" + ex.getMessage());
            return null;
        }
    }

    @Override
    public void write(List<BJDProduct> products, String outPutFilePath, ResultMSG resultMSG) {
        String fileName = "报价单.xls";
        String filePath = outPutFilePath + File.separator + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        try (Workbook wb = new HSSFWorkbook(); FileOutputStream fos = new FileOutputStream(file)) {
            //Workbook wb = new XSSFWorkbook();
            Map<String, CellStyle> styles = createStyles(wb);
            //CreationHelper createHelper = wb.getCreationHelper();
            Sheet sheet = wb.createSheet("报价单");

            sheet.setColumnWidth(0, 256 * 16);
            sheet.setColumnWidth(1, 256 * 10);
            sheet.setColumnWidth(2, 256 * 10);
            sheet.setColumnWidth(3, 256 * 10);
            sheet.setColumnWidth(4, 256 * 10);
            sheet.setColumnWidth(5, 256 * 10);
            sheet.setColumnWidth(6, 256 * 14);
            //Title
            //head
            Row headRow = sheet.createRow(0);
            Cell headCell = headRow.createCell(0);
            //height
            headRow.setHeightInPoints(30);
            //style
            headCell.setCellStyle(styles.get("head"));
            //value
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月");
            headCell.setCellValue("报价单" + sdf.format(new Date()));
            //title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
            Row titleRow = sheet.createRow(1);
            titleRow.setHeightInPoints(20);
            Cell titleCell0 = titleRow.createCell(0);
            titleCell0.setCellValue("款号");
            titleCell0.setCellStyle(styles.get("title"));
            Cell titleCell1 = titleRow.createCell(1);
            titleCell1.setCellValue("原价");
            titleCell1.setCellStyle(styles.get("title"));
            Cell titleCell2 = titleRow.createCell(2);
            titleCell2.setCellValue("折扣");
            titleCell2.setCellStyle(styles.get("title"));
            Cell titleCell3 = titleRow.createCell(3);
            titleCell3.setCellValue("折后价");
            titleCell3.setCellStyle(styles.get("title"));
            Cell titleCell4 = titleRow.createCell(4);
            titleCell4.setCellValue("年份");
            titleCell4.setCellStyle(styles.get("title"));
            Cell titleCell5 = titleRow.createCell(5);
            titleCell5.setCellValue("季节");
            titleCell5.setCellStyle(styles.get("title"));
            Cell titleCell6 = titleRow.createCell(6);
            titleCell6.setCellValue("品类");
            titleCell6.setCellStyle(styles.get("title"));
            //遍历
            
            //Discount
            DiscountUtil discountUtil = new DiscountUtil();
            List<Discount> discounts = discountUtil.read();
            
            Iterator<BJDProduct> iter = products.iterator();
            int num_row = titleRow.getRowNum() + 1;
            while (iter.hasNext()) {
                BJDProduct product = iter.next();  //获得品类等
                if (product != null) {
                    Row row = sheet.createRow(num_row);
                    row.setHeightInPoints(15);
                    List<String> values = new ArrayList<String>();
                    values.add(product.getSnCode());
                    values.add(Double.toString(product.getPrice()));
                    //persent
                    String persent = discountUtil.getPercent(product.getSnCode(), discounts);
                    values.add(persent);
                    values.add("");
                    values.add(product.getYear());
                    values.add(product.getSeason());
                    values.add(product.getType());
                    for (int col = 0; col < 7; col++) {
                        Cell cell = row.createCell(col);
                        if (col == 3) {
                            String formula = "INT(B" + (num_row + 1) + "*C" + (num_row + 1) + ")";
                            cell.setCellFormula(formula);
                        } else {
                            cell.setCellValue(values.get(col));
                        }
                        cell.setCellStyle(styles.get("data"));
                    }
                    num_row++;
                }
            }
            //wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            wb.getSheetAt(0).setForceFormulaRecalculation(true);
            // Write the output to a file
            wb.write(fos);
            resultMSG.setWriteMessage("写入报价单完成");
        } catch (Exception ex) {
            resultMSG.setErrorMessage("写入报价单出错!" + ex.getMessage());
        }
    }

    /**
     * cell styles used for formatting calendar sheets
     */
    private Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        CellStyle style;
        //head
        Font headFont = wb.createFont();
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(headFont);
        styles.put("head", style);

        //title
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFont(titleFont);
        styles.put("title", style);

        //data
        Font dataFont = wb.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setFont(dataFont);
        styles.put("data", style);

        return styles;
    }

}
