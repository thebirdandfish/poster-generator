package com.fermedu.poster.controller;

import com.fermedu.poster.entity.CompanyInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Program: poster-generator
 * @Create: 2021-02-16 20:25
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Controller
@Api(tags = {"index"})
public class IndexController {

    @ApiOperation("index")
    @GetMapping(value = {"/", "/index"})
    public ModelAndView index() {

        return new ModelAndView("index");
    }
}
