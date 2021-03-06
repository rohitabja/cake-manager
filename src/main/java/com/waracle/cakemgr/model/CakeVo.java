package com.waracle.cakemgr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CakeVo {

    private Integer id;
    private String name;
    private String desc;
    private String image;

}
