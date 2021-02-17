package com.fermedu.poster.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Program: poster-generator
 * @Create: 2021-02-16 21:20
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Data
public class CompanyInfoForm {

    @NotEmpty(message="It should not be empty")
    private String companyName;

    @NotEmpty(message="It should not be empty")
    private String companyLogo;

    @NotEmpty(message="It should not be empty")
    private String companyWebsite;
}
