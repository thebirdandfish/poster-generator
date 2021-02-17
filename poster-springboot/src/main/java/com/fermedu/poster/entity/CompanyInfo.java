package com.fermedu.poster.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * @Program: poster-generator
 * @Create: 2021-02-16 21:07
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@XmlRootElement
@Data
public class CompanyInfo implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    private String companyName;

    private String companyLogo;

    private String companyWebsite;
}
