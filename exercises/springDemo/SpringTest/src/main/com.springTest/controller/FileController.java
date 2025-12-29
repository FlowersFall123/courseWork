package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;

@Controller
public class FileController {

    /**
     * 需要配置web.xml
     * <multipart-config>
     *   <max-file-size>10485760</max-file-size>
     *   <max-request-size>20971520</max-request-size>
     *   <file-size-threshold>0</file-size-threshold>
     * </multipart-config>
     * @param file
     * @param request
     * @throws IOException
     */
    ，
    @PostMapping("/upload")
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        long start=System.currentTimeMillis();
        File file1=new File(request.getSession().getServletContext().getRealPath("/upload"));
        System.out.println("file1 = " + file1);
        if(!file1.exists()){
            file1.mkdirs();
        }
        String filePath=request.getSession().getServletContext().getRealPath("/upload")+"/"
                +new Date().getTime()+file.getOriginalFilename();
        OutputStream outputStream= new FileOutputStream(filePath);
        InputStream inputStream=file.getInputStream();
        int temp;
        while((temp=inputStream.read())!=(-1)){
            outputStream.write(temp);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        long end=System.currentTimeMillis();
        System.out.println("上传文件耗时："+(end-start));
    }
}
