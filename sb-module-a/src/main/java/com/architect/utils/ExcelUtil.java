package com.architect.utils;

import com.architect.annotation.ModelProp;
import com.architect.annotation.ModelTitle;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wenxiong.jia
 * @since 2018/9/26
 */
public class ExcelUtil {
    private final static int COLSIZEN = 630;
    private final static int COLSIZEM = 1000;

    public static InputStream excelModelbyClass(Class<?> clazz, Map<Integer, String[]> map, Integer rowSize) {
        try {
            if (!clazz.isAnnotationPresent(ModelTitle.class)) {
                throw new Exception("请在此类型中加上ModelTitle注解");
            }
            if (rowSize == null) {
                rowSize = 1000;
            }
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
            /**
             * 设置标题样式
             */
            HSSFCellStyle titleStyle = wb.createCellStyle();
            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeight((short) 400);
            titleStyle.setFont(font);
            // 创建第一行，并在该行创建单元格，设置内容，做
            HSSFCell titleCell = sheet.createRow(0).createCell(0);
            /**
             * 获取标题
             */
            ModelTitle modelTitle = clazz.getAnnotation(ModelTitle.class);
            titleCell.setCellValue(new HSSFRichTextString(modelTitle.name()));
            titleCell.setCellStyle(titleStyle);

            Field[] fields = clazz.getDeclaredFields();
            HSSFRow headRow = sheet.createRow(1);
            int colSzie = 0;
            /**
             * 设置表头样式
             */
            HSSFCellStyle headStyle = wb.createCellStyle();
            headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            HSSFFont headFont = wb.createFont();
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            headFont.setFontHeight((short) 240);
            headStyle.setFont(headFont);
            List<Integer> cells = new ArrayList<Integer>();

            for (Field field : fields) {
                if (field.isAnnotationPresent(ModelProp.class)) {
                    ModelProp modelProp = field.getAnnotation(ModelProp.class);
                    if (modelProp.colIndex() == -1)
                        continue;
                    cells.add(modelProp.colIndex());
                    HSSFCell cell = headRow.createCell(modelProp.colIndex());
                    cell.setCellValue(new HSSFRichTextString(modelProp.name()));
                    cell.setCellStyle(headStyle);
                    colSzie++;
                    sheet.autoSizeColumn((short) modelProp.colIndex());
                    sheet.setColumnWidth(modelProp.colIndex(), modelProp.name().length() * COLSIZEN + COLSIZEM);

                    // 设置列为下拉框格式
                    if (map != null && map.get(new Integer(modelProp.colIndex())) != null) {
                        DVConstraint constraint = DVConstraint
                                .createExplicitListConstraint(map.get(modelProp.colIndex()));
                        CellRangeAddressList regions = new CellRangeAddressList(2, rowSize, modelProp.colIndex(),
                                modelProp.colIndex());
                        HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
                        sheet.addValidationData(dataValidation);
                    }
                }
            }
            HSSFCellStyle cellStyle = wb.createCellStyle();
            HSSFDataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("@"));
            for (int i = 2; i < rowSize; i++) {
                HSSFRow row = sheet.createRow(i);
                for (Integer integer : cells) {
                    HSSFCell cell = row.createCell(integer);
                    cell.setCellStyle(cellStyle);
                }
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSzie - 1));
            if (map != null) {
                for (Integer colIndex : map.keySet()) {
                    DVConstraint constraint = DVConstraint.createExplicitListConstraint(map.get(colIndex));
                    CellRangeAddressList regions = new CellRangeAddressList(2, 1000, colIndex, colIndex);
                    HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
                    sheet.addValidationData(dataValidation);
                }
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                wb.write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] b = os.toByteArray();

            ByteArrayInputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
