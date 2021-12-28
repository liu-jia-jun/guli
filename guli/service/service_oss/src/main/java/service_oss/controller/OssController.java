package service_oss.controller;

import com.commonutils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service_oss.service.OssService;

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
    public Result uploadOssFile(MultipartFile multipartFile){
        // 获取上传文件 MultipartFile
        String url = ossService.uploadFileAvatar(multipartFile);
        return Result.ok().data("url",url);
    }
    @GetMapping("/hello")
    public Result test(){
        System.out.println("hello world");
        return Result.ok();
    }
}
