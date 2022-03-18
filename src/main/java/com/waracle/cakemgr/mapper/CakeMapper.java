package com.waracle.cakemgr.mapper;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.entity.User;
import com.waracle.cakemgr.model.CakeVo;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface CakeMapper {

    Cake mapToEntity(@NotNull CakeVo cakeVo);

    Cake mapToEntity(@NotNull CakeVo cakeVo, @NotNull User user);

    Collection<Cake> mapToEntity(Collection<CakeVo> cakeVos);

    CakeVo mapToVo(@NotNull Cake cake);

    Collection<CakeVo> mapToVo(Collection<Cake> cakes);
}
