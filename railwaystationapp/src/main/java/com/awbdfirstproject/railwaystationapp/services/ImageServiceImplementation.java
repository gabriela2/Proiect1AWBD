package com.awbdfirstproject.railwaystationapp.services;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImageServiceImplementation implements ImageServiceInterface {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file) {
        try {
            Company company = companyRepository.findById(id).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }


            company.setImage(byteObjects);
            companyRepository.save(company);
        } catch (IOException e) {
        }

    }
}
