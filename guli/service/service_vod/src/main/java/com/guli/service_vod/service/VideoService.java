package com.guli.service_vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 刘佳俊
 */
public interface VideoService {

    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);
    void removeVideoList(List<String> videoIdList);
}
