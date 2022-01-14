package service_vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘佳俊
 */
public interface VideoService {

    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);
}
