package com.coast.service.impl;

import com.coast.model.DRPProduct;
import com.coast.model.Excel2DRPList;
import com.coast.model.ResultMSG;
import com.coast.service.Excel2DRPService;
import com.coast.util.POIUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
public class Excel2DRPServiceImpl implements Excel2DRPService {

    private static final String NEXT_LINE = System.getProperty("line.separator");

    @Override
    public List<DRPProduct> readExcel(String ourExcelFilePath, ResultMSG resultMSG) {
        //1.加载Excel
        //2.去除最后的唯一码,保留13位.
        //3.添加到ArrayList并合并相同的款号

        //1.加载Excel
        File file = new File(ourExcelFilePath);
        try (InputStream is = new FileInputStream(file); Workbook wb = WorkbookFactory.create(is)) {

            //准备空List
            List<DRPProduct> products = new ArrayList<>();
            int row = 0;
            //merge sheet?
            Iterator<Sheet> sheetIterator = wb.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                String sheetName = sheet.getSheetName();
                int rowcount = readSheetData(sheet, products);
                row += rowcount;
                resultMSG.setReadMessage(resultMSG.getReadMessage() + "-" + sheetName + "-读取:" + rowcount + "条记录." + NEXT_LINE);
            }
            resultMSG.setReadMessage(resultMSG.getReadMessage() + "==读取[" + file.getName() + "]完成:共" + row + "行!==" + NEXT_LINE);
            return products;
        } catch (Exception ex) {
            resultMSG.setErrorMessage("读取Excel2DRP" + file.getName() + "出错!!!" + ex.toString() + NEXT_LINE);
            return null;
        }

    }

    @Override
    public void write(List<DRPProduct> products, String outPutFilePath, ResultMSG resultMSG, String ourExcelFilePath) {
        File ourExcelFile = new File(ourExcelFilePath);
        String sourceFileName = ourExcelFile.getName();
        String fileName = sourceFileName.substring(0, sourceFileName.lastIndexOf(".")) + "_drp.xls";
        String filePath = outPutFilePath + File.separator + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        try (Workbook wb = new HSSFWorkbook(); FileOutputStream fos = new FileOutputStream(file)) {
            Sheet sheet = wb.createSheet("DRP");

            //Title
            //head
            Row headRow = sheet.createRow(0);
            //SN
            Cell SnCodeheadCell = headRow.createCell(0);
            SnCodeheadCell.setCellValue("款号(13位)");
            //Amount
            Cell amountHeadCellCell = headRow.createCell(1);
            amountHeadCellCell.setCellValue("数量");
            //遍历
            Iterator<DRPProduct> iter = products.iterator();
            int num_row = headRow.getRowNum() + 1;
            int sum = 0;
            while (iter.hasNext()) {
                DRPProduct product = iter.next();
                if (product != null) {
                    Row row = sheet.createRow(num_row);
                    Cell snCell = row.createCell(0);
                    snCell.setCellValue(product.getSnCode());
                    Cell amountCell = row.createCell(1);
                    amountCell.setCellValue(product.getAmount());
                    sum += product.getAmount();
                    num_row++;
                }
            }
            //wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            wb.getSheetAt(0).setForceFormulaRecalculation(true);
            // Write the output to a file
            wb.write(fos);
            resultMSG.setWriteMessage("==写入[" + fileName + "]完成,共" + sum + "件!==" + NEXT_LINE);
        } catch (Exception ex) {
            resultMSG.setErrorMessage("!!!写入Excel2DRP出错!!!" + ex.toString() + NEXT_LINE);
        }
    }
    //读取sheet中的数据并添加到已有的List,返回读取的行数

    private int readSheetData(Sheet sheet, List<DRPProduct> products) {
        POIUtil poiUtil = new POIUtil();

        int row = 0;
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row r = rowIterator.next();
            Iterator<Cell> cellIterator = r.cellIterator();
            Cell snCodeCell = cellIterator.next();
            if (!"".equals(poiUtil.getCellContentToString(snCodeCell).trim())) {
                //S540412D00abc
                String snCode = poiUtil.getCellContentToString(snCodeCell);

                //2.去除最后的唯一码,保留13位.
                snCode = snCode.substring(0, 13).toUpperCase();
                DRPProduct drpProduct = new DRPProduct(snCode, 1);

                //3.添加到ArrayList并合并相同的款号
                if (!products.contains(drpProduct)) {
                    products.add(drpProduct);
                } else {
                    for (DRPProduct product : products) {
                        if (product.equals(drpProduct)) {
                            product.setAmount(product.getAmount() + 1);
                        }
                    }
                }
                //处理下一行
                row++;
            }
        }
        return row;
    }

    @Override
    public List<Excel2DRPList> readExcelSheets(String ourExcelFilePath, ResultMSG resultMSG) {
        List<Excel2DRPList> sheetDatas = new ArrayList<>();

        File file = new File(ourExcelFilePath);
        try (InputStream is = new FileInputStream(file); Workbook wb = WorkbookFactory.create(is)) {
            //read sheetName and data
            Iterator<Sheet> sheetIterator = wb.sheetIterator();
            //总行数
            int row = 0;
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                //name
                String sheetName = sheet.getSheetName();
                //data
                List<DRPProduct> drpProducts = new ArrayList<>();
                int rowCount = readSheetData(sheet, drpProducts);
                sheetDatas.add(new Excel2DRPList(sheetName, drpProducts));

                row += rowCount;
                resultMSG.setReadMessage(resultMSG.getReadMessage() + "-" + sheetName + "-读取:" + rowCount + "条记录." + NEXT_LINE);
            }
            resultMSG.setReadMessage(resultMSG.getReadMessage() + "==读取[" + file.getName() + "]完成:共" + row + "行!==" + NEXT_LINE);
            return sheetDatas;
        } catch (Exception ex) {
            resultMSG.setErrorMessage("!!!读取Excel2DRP[" + file.getName() + "]出错!!!" + ex.toString() + NEXT_LINE);
            return null;
        }
    }

    @Override
    public void writeMultipleSheets(List<Excel2DRPList> sheetDatas, String outPutFilePath, ResultMSG resultMSG, String ourExcelFilePath) {
        if (sheetDatas.size() != 0) {
            Iterator<Excel2DRPList> iterator = sheetDatas.iterator();
            int amount = 0;
            while (iterator.hasNext()) {
                Excel2DRPList e2dList = iterator.next();
                String sheetName = e2dList.getName();
                List<DRPProduct> products = e2dList.getProducts();
                File ourExcelFile = new File(ourExcelFilePath);
                String sourceFileName = ourExcelFile.getName();
                String fileName = sourceFileName.substring(0, sourceFileName.lastIndexOf(".")) + "(" + sheetName + ")" + "_drp.xls";
                String filePath = outPutFilePath + File.separator + fileName;
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
                try (Workbook wb = new HSSFWorkbook(); FileOutputStream fos = new FileOutputStream(file)) {
                    Sheet sheet = wb.createSheet(sheetName);
                    //Title
                    //head
                    Row headRow = sheet.createRow(0);
                    //SN
                    Cell SnCodeheadCell = headRow.createCell(0);
                    SnCodeheadCell.setCellValue("款号(13位)");
                    //Amount
                    Cell amountHeadCellCell = headRow.createCell(1);
                    amountHeadCellCell.setCellValue("数量");
                    //遍历
                    Iterator<DRPProduct> iter = products.iterator();
                    int num_row = headRow.getRowNum() + 1;
                    int sum = 0;
                    while (iter.hasNext()) {
                        DRPProduct product = iter.next();
                        if (product != null) {
                            Row row = sheet.createRow(num_row);
                            Cell snCell = row.createCell(0);
                            snCell.setCellValue(product.getSnCode());
                            Cell amountCell = row.createCell(1);
                            amountCell.setCellValue(product.getAmount());
                            sum += product.getAmount();
                            num_row++;
                        }
                    }
                    amount += sum;
                    wb.getSheetAt(0).setForceFormulaRecalculation(true);
                    // Write the output to a file
                    wb.write(fos);
                    resultMSG.setWriteMessage(resultMSG.getWriteMessage() + "-" + sheetName + "-写入[" + fileName + "]完成,共" + sum + "件!" + NEXT_LINE);
                } catch (Exception ex) {
                    resultMSG.setErrorMessage("!!!写入Excel2DRP出错!!!" + ex.toString());
                }
            }
            resultMSG.setWriteMessage(resultMSG.getWriteMessage() + "==写入Excel2DRP完成,共 " + amount + " 件!==" + NEXT_LINE);
        }
    }

}
