package com.architect.controller;

import com.architect.dto.EmployeeDTO;
import com.architect.entity.User;
import com.architect.util.ExcelUtil;
import com.architect.util.ExportExcel;
import com.architect.util.FileUtil;
import com.architect.util.ImportExcel;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenxiong.jia
 * @since 2018/9/26
 */
@Slf4j
@RestController
@RequestMapping("/api/excelOpera")
public class ExcelController {
    private static final String EXCELFILENAME_USER = "用户数据导入模板.xlsx";

    @RequestMapping(path = "/downloadEmployeeModel", method = RequestMethod.GET)
    public String downloadEmployeeModel(HttpServletResponse response) {
        try {
            response.setContentType("application/ms-txt");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(("eeelist").getBytes("UTF-8"), "iso-8859-1") + ".csv");
            Map<Integer, String[]> paramMap = new HashMap<>(2);
            //excel第三行为下拉选择框
            paramMap.put(2, new String[]{"man", "women"});
            BufferedInputStream input = new BufferedInputStream(ExcelUtil.excelModelbyClass(EmployeeDTO.class, paramMap,
                    null));
            byte buffBytes[] = new byte[1024];
            OutputStream os = response.getOutputStream();
            int read;
            while ((read = input.read(buffBytes)) != -1) {
                os.write(buffBytes, 0, read);
            }
            os.flush();
            os.close();
            input.close();
            return "success!";
        } catch (Exception e) {
            //TODO 记录日志
            return "fail!";
        }
    }

    @RequestMapping(path = "/downloadUserModel", method = RequestMethod.GET)
    public String downloadUserModel(HttpServletResponse response) {
        try {
            List<User> list = Lists.newArrayList();
            User user = new User();
            user.setId(1L);
            user.setName("william");
            user.setAddress("北京市昌平区");
            user.setAge(28);
            user.setPhone("15353714844");
            list.add(user);
            user = new User();
            user.setId(2L);
            user.setName("william");
            user.setAddress("北京市昌平区");
            user.setAge(28);
            user.setPhone("15353714844");
            list.add(user);
            new ExportExcel("", User.class, 2).setDataList(list).write(response, EXCELFILENAME_USER).dispose();
            return null;
        } catch (Exception e) {
            log.error("下载模板异常：", e);
            return "fail";
        }
    }

    @PostMapping("/importUserData")
    public String importUserData(MultipartFile file) {
        try {
            ImportExcel importExcel = new ImportExcel(file, 1, 0);
            List<User> list = importExcel.getDataList(User.class);
            int num = 0;
            for (User user : list) {
                if (user.getId() == null) {
                    user.setErrorInfoDesc("主键不能为空！");
                    num++;
                    continue;
                }
                System.out.println(list);
            }

            if (num > 0) {
                String fileName = "用户数据导入错误" + ".xlsx";
                new ExportExcel("", User.class, 1, 0, 1).setDataList(list)
                        .write(null, fileName).dispose();
                return null;
            }
            String filePath = "C:/Users/15353/Downloads/up/";
            // 把上传的文件放入指定的位置，同时路径存入数据库
            try {
                FileUtil.uploadFile(file, filePath);
                // 路径入库
            } catch (Exception e) {
                // TODO
            }
            return null;
        } catch (Exception e) {
            log.error("导入用户数据异常：", e);
            return "fail";
        }
    }
}
