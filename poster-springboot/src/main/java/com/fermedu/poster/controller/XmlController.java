package com.fermedu.poster.controller;

import cn.hutool.core.util.XmlUtil;
import com.fermedu.poster.entity.CompanyInfo;
import com.fermedu.poster.form.CompanyInfoForm;
import com.fermedu.poster.util.Poster;
import com.fermedu.poster.util.PosterUtil;
import com.fermedu.poster.util.QrcodeUtil;
import com.fermedu.poster.util.XmlFastUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.filechooser.FileSystemView;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @Program: poster-generator
 * @Create: 2021-02-16 21:15
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Controller
@Api("xml")
public class XmlController {

    @GetMapping(value = {"/xml/form"})
    @ApiOperation("index")

    public ModelAndView index(Map<String, Object> map) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyName("Google");
        companyInfo.setCompanyLogo("https://s3.ax1x.com/2021/02/16/yc7PI0.jpg");
        companyInfo.setCompanyWebsite("https://www.google.com/");
        map.put("companyInfo", companyInfo);

        return new ModelAndView("xml/form", map);
    }

    @PostMapping(value = {"/xml/generate"})
    @ApiOperation("generate")

    public ModelAndView generate(@Valid CompanyInfoForm companyInfoForm,
                                 BindingResult bindingResult,
                                 Map<String, Object> map) throws IOException {
        if (bindingResult.hasErrors()) {
            return null;
        } else {
            CompanyInfo companyInfo = new CompanyInfo();
            BeanUtils.copyProperties(companyInfoForm, companyInfo);

            String xmlString = XmlFastUtil.toStr(companyInfo, false, false);

            map.put("xmlString", xmlString);

            return new ModelAndView("xml/result", map);
        }

    }


}
