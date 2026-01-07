package com.example.devgroveback.controller;

import com.example.devgroveback.Response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadImgController {

    @Autowired(required = false)
    private ResourceLoader resourceLoader;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("uploadImg")
    public Response<String> uploadImg(@RequestParam("file")MultipartFile multipartFile) {
        System.out.println(multipartFile);
        try {
            if (multipartFile.isEmpty()) {
                return Response.newFail("文件为空，请重新选择!");
            }
            File file = new File(uploadPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            //获取原始名字
            String orgName = multipartFile.getOriginalFilename();
            String prefixName = "";
            String suffixName = "";
            String fileName;

            if (orgName != null) {
                prefixName = orgName.substring(0, orgName.lastIndexOf("."));
                suffixName = orgName.substring(orgName.lastIndexOf("."));
            }
            System.out.println(uploadPath);


            if (orgName.contains(".")) {
                fileName = prefixName + '_' + System.currentTimeMillis() + suffixName;
            } else {
                return Response.newFail("上传图片格式错误");
            }
            String savePath = file.getPath() + File.separator + fileName;
            File saveFile = new File(savePath);
            FileCopyUtils.copy(multipartFile.getBytes(), saveFile);
            String path = "upload" + File.separator + fileName;
            if (path.contains("\\")) {
                path = path.replace("\\", "/");
            }
            return Response.newSuccess(path);

        } catch(Exception e) {
            return Response.newFail("图片上传错误");
        }
    }
}
