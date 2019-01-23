package com.architect.util;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


import com.architect.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExportBeanExcel<T> {

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出
     *
     * title         表格标题名
     * headersName  表格属性列名数组
     * headersId    表格属性列名对应的字段---你需要导出的字段名（为了更灵活控制你想要导出的字段）
     *  dtoList     需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象
     *  out         与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public  void exportExcel(String title, List<String> headersName,List<String> headersId,
                             List<T> dtoList) {
        /*（一）表头--标题栏*/
        Map<Integer, String> headersNameMap = new HashMap<>();
        int key=0;
        for (int i = 0; i < headersName.size(); i++) {
            headersNameMap.put(key, headersName.get(i));
            key++;
        }
        /*（二）字段*/
        Map<Integer, String> titleFieldMap = new HashMap<>();
        int value = 0;
        for (int i = 0; i < headersId.size(); i++) {
            titleFieldMap.put(value, headersId.get(i));
            value++;
        }
        /* （三）声明一个工作薄：包括构建工作簿、表格、样式*/
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(title);
        sheet.setDefaultColumnWidth((short)15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        HSSFRow row = sheet.createRow(0);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell;
        //拿到表格所有标题的value的集合
        Collection c = headersNameMap.values();
        //表格标题的迭代器
        Iterator<String> it = c.iterator();
        /*（四）导出数据：包括导出标题栏以及内容栏*/
        //根据选择的字段生成表头
        short size = 0;
        while (it.hasNext()) {
            cell = row.createCell(size);
            cell.setCellValue(it.next().toString());
            cell.setCellStyle(style);
            size++;
        }
        //表格标题一行的字段的集合
        Collection zdC = titleFieldMap.values();
        //总记录的迭代器
        Iterator<T> labIt = dtoList.iterator();
        //列序号
        int zdRow =0;
        //记录的迭代器，遍历总记录
        while (labIt.hasNext()) {
            int zdCell = 0;
            zdRow++;
            row = sheet.createRow(zdRow);
            T l = (T) labIt.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            //获得JavaBean全部属性
            Field[] fields = l.getClass().getDeclaredFields();
            //遍历属性，比对
            for (short i = 0; i < fields.length; i++) {
                Field field = fields[i];
                //属性名
                String fieldName = field.getName();
                //一条字段的集合的迭代器
                Iterator<String> zdIt = zdC.iterator();
                //遍历要导出的字段集合
                while (zdIt.hasNext()) {
                    //比对JavaBean的属性名，一致就写入，不一致就丢弃
                    if (zdIt.next().equals(fieldName)) {
                        //拿到属性的get方法
                        String getMethodName = "get"
                                + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        //拿到JavaBean对象
                        Class tCls = l.getClass();
                        try {
                            //通过JavaBean对象拿到该属性的get方法，从而进行操控
                            Method getMethod = tCls.getMethod(getMethodName,
                                    new Class[] {});
                            //操控该对象属性的get方法，从而拿到属性值
                            Object val = getMethod.invoke(l, new Object[] {});
                            String textVal = null;
                            if (val!= null) {
                                //转化成String
                                textVal = String.valueOf(val);
                            }else{
                                textVal = null;
                            }
                            row.createCell((short) zdCell).setCellValue(textVal);//写进excel对象
                            zdCell++;
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        try {
            FileOutputStream exportXls = new FileOutputStream("/Users/linlg/工单信息表.xls");
            wb.write(exportXls);
            exportXls.close();
            System.out.println("导出成功!");
        } catch (FileNotFoundException e) {
            System.out.println("导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("导出失败!");
            e.printStackTrace();
        }
    }
    /*
        使用例子
    */
    public static void main(String [] args){
        List<String> listName = new ArrayList<>();
        listName.add("id");
        listName.add("名字");
        listName.add("性别");
        List<String> listId = new ArrayList<>();
        listId.add("id");
        listId.add("name");
        listId.add("sex");
        List<Student> list = new ArrayList<>();
        list.add(new Student(111,"张三asdf","男"));
        list.add(new Student(111,"李四asd","男"));
        list.add(new Student(111,"王五","女"));


        ExportBeanExcel<Student> exportBeanExcelUtil = new ExportBeanExcel();
        exportBeanExcelUtil.exportExcel("测试POI导出EXCEL文档",listName,listId,list);

    }
}