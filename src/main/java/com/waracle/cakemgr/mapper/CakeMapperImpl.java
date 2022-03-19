package com.waracle.cakemgr.mapper;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.entity.User;
import com.waracle.cakemgr.model.CakeVo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
class CakeMapperImpl implements CakeMapper {

    @Override
    public Cake mapToEntity(@NotNull final CakeVo cakeVo) {
        return Cake.builder()
                .cakeId(cakeVo.getId())
                .name(cakeVo.getName())
                .description(cakeVo.getDesc())
                .image(cakeVo.getImage())
                .build();
    }

    @Override
    public Cake mapToEntity(@NotNull final CakeVo cakeVo, @NotNull final User user) {
        final List<User> users = new ArrayList<>();
        users.add(user);
        return Cake.builder()
                .cakeId(cakeVo.getId())
                .name(cakeVo.getName())
                .description(cakeVo.getDesc())
                .image(cakeVo.getImage())
                .users(users)
                .build();
    }

    @Override
    public Collection<Cake> mapToEntity(final Collection<CakeVo> cakeVos) {
        if (CollectionUtils.isEmpty(cakeVos)) {
            return Collections.emptyList();
        }

        return cakeVos.stream().map(cakeVo ->
                        Cake.builder()
                                .cakeId(cakeVo.getId())
                                .name(cakeVo.getName())
                                .description(cakeVo.getDesc())
                                .image(cakeVo.getImage())
                                .build())
                .collect(Collectors.toList());
    }

    @Override
    public CakeVo mapToVo(@NotNull final Cake cake) {
        return CakeVo.builder()
                .id(cake.getCakeId())
                .name(cake.getName())
                .desc(cake.getDescription())
                .image(cake.getImage())
                .build();
    }

    @Override
    public Collection<CakeVo> mapToVo(final Collection<Cake> cakes) {
        if (CollectionUtils.isEmpty(cakes)) {
            return Collections.emptyList();
        }

        return cakes.stream().map(cake ->
                        CakeVo.builder()
                                .id(cake.getCakeId())
                                .name(cake.getName())
                                .desc(cake.getDescription())
                                .image(cake.getImage())
                                .build())
                .collect(Collectors.toList());
    }
}
