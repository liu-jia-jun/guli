package service_oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘佳俊
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile multipartFile);
}
