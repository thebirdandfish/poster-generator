package com.fermedu.poster.controller;

import cn.hutool.core.util.XmlUtil;
import com.fermedu.poster.entity.CompanyInfo;
import com.fermedu.poster.form.CompanyInfoForm;
import com.fermedu.poster.util.*;
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
import org.w3c.dom.Document;

import javax.swing.filechooser.FileSystemView;
import javax.validation.Valid;
import javax.xml.xpath.XPathConstants;
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
@Api(tags = {"poster"})
public class PosterController {
    @GetMapping(value = {"/poster/input"})
    @ApiOperation("input")

    public ModelAndView input(Map<String, Object> map) {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyName("Google");
        companyInfo.setCompanyLogo("https://s3.ax1x.com/2021/02/16/yc7PI0.jpg");
        companyInfo.setCompanyWebsite("https://www.google.com/");


        map.put("companyInfo", companyInfo);

        return new ModelAndView("poster/input", map);
    }

    @PostMapping(value = {"/poster/generate"}, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    @ApiOperation("generate")

    public byte[] generate(@Valid CompanyInfoForm companyInfoForm,
                           BindingResult bindingResult,
                           Map<String, Object> map) throws Exception {
        if (bindingResult.hasErrors()) {
            return null;
        }
        CompanyInfo companyInfo = new CompanyInfo();
        BeanUtils.copyProperties(companyInfoForm, companyInfo);

        String qrcodePath = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "1.jpg";
        String pngImgPath = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "1.png";

        QrcodeUtil.createQrCode(companyInfo.getCompanyWebsite(), qrcodePath);

        String abosolutePath = PosterFastUtil.drawPoster(companyInfo, qrcodePath, pngImgPath);

        FileInputStream inputStream = new FileInputStream(abosolutePath);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());

        return bytes;
    }


}
