package com.architect.test;

import com.architect.controller.ExcelController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author wenxiong.jia
 * @since 2018/7/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExcelController.class)
public class ImportFileTest {

    @Autowired
    private ExcelController excelController;

    @Test
    public void testImportFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:/Users/15353/Downloads/用户数据导入模板.xlsx");
            MockMultipartFile mfile = new MockMultipartFile("C:/Users/15353/Downloads",
                    "用户数据导入模板.xlsx", "", fileInputStream);
            excelController.importUserData(mfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
