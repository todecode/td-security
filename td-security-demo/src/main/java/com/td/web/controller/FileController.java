package com.td.web.controller;

import com.td.dto.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @className: FileController
 * @description:
 * @author: cyd
 * @date: 2021/4/6 下午3:22
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 默认下载到当前fileController目录层
     */
    private String folder = "/Users/chenyd/my/td-security/td-security-demo/src/main/java/com/td/web/controller";

    @PostMapping
    public FileInfo update(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        File localFile = new File(folder, System.currentTimeMillis()+".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: 2019/12/1 jdk7关闭留时候放在try括号里， 会默认关闭里。无需手动关闭流
        try (InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
             OutputStream outputStream = response.getOutputStream();){

            response.setContentType("application/x-download");
            // 设置context以及返回的filename文件名
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            // 复制流。将输入流复制到输出流
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }
}
