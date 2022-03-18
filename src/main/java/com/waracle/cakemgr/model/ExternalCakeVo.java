package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExternalCakeVo {

    private Integer id;
    private String title;
    private String desc;
    private String image;

}
