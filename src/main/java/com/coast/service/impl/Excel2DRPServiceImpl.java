package com.coast.service.impl;

import com.coast.model.DRPProduct;
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

    @Override
    public List<DRPProduct> readExcel(String ourExcelFilePath, ResultMSG resultMSG) {
        //1.加载Excel
        //2.去除最后的唯一码,保留13位.
        //3.添加到ArrayList并合并相同的款号

        //1.加载Excel
        File file = new File(ourExcelFilePath);
        try (InputStream is = new FileInputStream(file); Workbook wb = WorkbookFactory.create(is)) {
            POIUtil poiUtil = new POIUtil();
            Sheet sheet = wb.getSheetAt(0);

            List<DRPProduct> products = new ArrayList<>();
            int row = 0;
            int lastRowNum = sheet.getLastRowNum();
            while (row <= lastRowNum) {
                //sn S540412D00abcd
                Cell snCodeCell = sheet.getRow(row).getCell(0);
                if (snCodeCell.getCellType() == Cell.CELL_TYPE_BLANK || snCodeCell == null) {
                    break;
                }
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
            resultMSG.setReadMessage("读取Excel2DRP完成:共" + row + "行!");
            return products;
        } catch (Exception ex) {
            resultMSG.setErrorMessage("读取Excel2DRP出错!" + ex.toString());
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
            Sheet sheet = wb.createSheet("报价单");

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
            while (iter.hasNext()) {
                DRPProduct product = iter.next();
                if (product != null) {
                    Row row = sheet.createRow(num_row);
                    Cell snCell = row.createCell(0);
                    snCell.setCellValue(product.getSnCode());
                    Cell amountCell = row.createCell(1);
                    amountCell.setCellValue(product.getAmount());
                    num_row++;
                }
            }
            //wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            wb.getSheetAt(0).setForceFormulaRecalculation(true);
            // Write the output to a file
            wb.write(fos);
            resultMSG.setWriteMessage("写入Excel2DRP完成");
        } catch (Exception ex) {
            resultMSG.setErrorMessage("写入Excel2DRP出错!" + ex.toString());
        }
    }

}
