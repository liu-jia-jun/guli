package com.guli.service_oss.controller;

import com.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.guli.service_oss.service.OssService;

/**
 * @author 刘佳俊
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping("/uploadFile")
    public Result uploadOssFile(MultipartFile file){
        // 获取上传文件 MultipartFile
        String url = ossService.uploadFileAvatar(file);

        return Result.ok().data("url",url);
    }

}
