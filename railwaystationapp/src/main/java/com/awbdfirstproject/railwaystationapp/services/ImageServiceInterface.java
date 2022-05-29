package com.awbdfirstproject.railwaystationapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageServiceInterface {
    void saveImageFile(Long id, MultipartFile file);
}
