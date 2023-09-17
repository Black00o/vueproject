package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.example.springboot.common.AuthAccess;
import com.example.springboot.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 功能：
 * 作者：lhy
 * 日期：2023/8/28 10:51
 */

@RestController
@RequestMapping("/file")
public class FileControlller {

    @Value("${ip:localhost}")
    String ip;

    @Value("${server.port}")
    String port;

    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "files";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();//文件的原始名称
        String mainName = FileUtil.mainName(originalFilename);//不包含后缀的文件名称
        String extName = FileUtil.extName(originalFilename);

        if(!FileUtil.exist(ROOT_PATH)){
            FileUtil.mkdir(ROOT_PATH);
        }

        if(FileUtil.exist(ROOT_PATH + File.separator + originalFilename)){
            originalFilename = System.currentTimeMillis() + "_" + mainName + "." + extName;

        }
        File saveFile = new File(ROOT_PATH + File.separator + originalFilename);
        file.transferTo(saveFile);
        String url = "http://" + ip + ":" + port + "/file/download/" + originalFilename;
        return Result.success(url);   //返回文件的链接
    }

    @AuthAccess
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {

//        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // 附件下载
        response.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // 预览
        String filePath = ROOT_PATH + File.separator + fileName;
        if(!FileUtil.exist(filePath)){
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);  //文件的数据字节数组
        outputStream.flush();
        outputStream.close();

    }
}