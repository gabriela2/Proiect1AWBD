package com.awbdfirstproject.railwaystationapp.controller;

import com.awbdfirstproject.railwaystationapp.domain.Company;
import com.awbdfirstproject.railwaystationapp.dto.CompanyDto;
import com.awbdfirstproject.railwaystationapp.mapper.CompanyMapper;
import com.awbdfirstproject.railwaystationapp.services.CompanyServiceInterface;
import com.awbdfirstproject.railwaystationapp.services.ImageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CompanyController {
    @Autowired
    CompanyServiceInterface companyServiceInterface;
    @Autowired
    CompanyMapper companyMapper;
    @Autowired
    ImageServiceInterface imageServiceInterface;

    @GetMapping(path = "/company/company-form")
    public String companyForm(Model model) {
        model.addAttribute("company", new CompanyDto());
        return "company-form";
    }


    @PostMapping("/company/company-form")
    public String register(@Valid @ModelAttribute CompanyDto companyDto, @RequestParam("imagefile") MultipartFile file) {
        Company company = companyServiceInterface.save(companyMapper.companyDtoToCompany(companyDto));
        imageServiceInterface.saveImageFile(company.getId(), file);
        return "redirect:/admin/company";
    }

    @GetMapping(path = "/company")
    public ModelAndView company() {
        ModelAndView modelAndView = new ModelAndView("company");
        List<Company> companies = companyServiceInterface.findAll();
        modelAndView.addObject("companies", companies);
        return modelAndView;
    }

    @RequestMapping("/company/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        companyServiceInterface.deleteById(id);
        return "redirect:/admin/company";
    }

    @GetMapping(path = "/company/update/{id}")
    public String companyUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyServiceInterface.findById(id));
        return "company-update";
    }

    @RequestMapping("/company/update/{id}")
    public String updateById(@PathVariable Long id, @Valid @ModelAttribute CompanyDto companyDto) {
        companyServiceInterface.update(id, companyDto);
        return "redirect:/admin/company";
    }
}
