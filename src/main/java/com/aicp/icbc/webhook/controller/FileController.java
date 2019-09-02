package com.aicp.icbc.webhook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 更新服务器文件
 * @Author: 吴开云
 * @Date: 2019/9/2 0002
 * @Version： 1.0
 */
@Controller
public class FileController {

    @RequestMapping("/update")
    @ResponseBody
    public String updateFile(HttpServletRequest request) throws IOException, ServletException {
        //获取文件流
        Collection<Part> parts = request.getParts();
        //累积接受的文件名
        String filePath = "";
        int size = 0;
        byte[] buffer = new byte[1024];
        for (Part perPart:parts) {
            //获取输入文件名
            String name = perPart.getSubmittedFileName();
            //获取classPath路径--利用配置文件
            String path = this.getClass().getClassLoader().getResource("repalaceFileName.txt").getPath();
            //替换目标文件名
            String goalPath = path.replace("repalaceFileName.txt",name);
            //获取输入流和输入流
            InputStream input = perPart.getInputStream();
            FileOutputStream fos = new FileOutputStream(goalPath);
            filePath += goalPath + "\t";
            //读取文件并写出
            while ((size = input.read(buffer,0,1024)) != -1) {
                fos.write(buffer, 0, size);
            }
            if (fos != null){
                fos.close();
            }
            if (input!= null){
                input.close();
            }
            //perPart.write(name);
        }
        return "success updata:\t" + filePath;
    }
}
