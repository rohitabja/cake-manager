package com.waracle.cakemgr.service;

import com.waracle.cakemgr.model.CakeVo;
import com.waracle.cakemgr.model.CakeResponse;
import com.waracle.cakemgr.model.CreateCakeRequest;
import com.waracle.cakemgr.model.CreateCakeResponse;

import javax.validation.constraints.NotNull;

public interface CakeService {

    CakeResponse getAllCakes();

    CakeVo getCakeForId(int id);

    CakeVo createCake(@NotNull CakeVo cake);

    CreateCakeResponse createCakes(@NotNull CreateCakeRequest createCakeRequest);

}
