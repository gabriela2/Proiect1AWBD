package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.services.CompanyServiceInterface;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    @Autowired
    private CompanyServiceInterface companyServiceInterface;

    @GetMapping("company/getImage/{id}")
    public void downloadImage(@PathVariable long id, HttpServletResponse response) throws IOException {
        Company company = companyServiceInterface.findById(id);

        if (company.getImage() != null) {
            byte[] byteArray = new byte[company.getImage().length];
            int i = 0;
            for (Byte wrappedByte : company.getImage()) {
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            try {
                IOUtils.copy(is, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
