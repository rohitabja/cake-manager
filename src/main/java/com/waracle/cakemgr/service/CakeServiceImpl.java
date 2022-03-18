package com.waracle.cakemgr.service;

import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.exception.CakeNotFoundException;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.CakeVo;
import com.waracle.cakemgr.model.CakeResponse;
import com.waracle.cakemgr.model.CreateCakeRequest;
import com.waracle.cakemgr.model.CreateCakeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class CakeServiceImpl implements CakeService {

    private final CakeRepository cakeRepository;
    private final CakeMapper cakeMapper;

    @Override
    public CakeResponse getAllCakes() {
        final List<Cake> allCakes = cakeRepository.findAll();
        return CakeResponse.builder().cakes(cakeMapper.mapToVo(allCakes)).build();
    }

    @Override
    public CakeVo getCakeForId(final int id) {
        final Optional<Cake> cakeFromDb = cakeRepository.findById(id);
        return cakeFromDb.map(cake -> CakeVo.builder()
                        .id(cake.getId())
                        .title(cake.getName())
                        .desc(cake.getDescription())
                        .image(cake.getImage())
                        .build())
                .orElseThrow(() -> new CakeNotFoundException(String.format("Cake not found for id %s", id)));
    }

    @Override
    public CakeVo createCake(@NotNull final CakeVo cake) {
        final Cake savedCake = cakeRepository.save(cakeMapper.mapToEntity(cake));
        return cakeMapper.mapToVo(savedCake);
    }

    @Override
    public CreateCakeResponse createCakes(final CreateCakeRequest createCakeRequest) {
        final List<Cake> savedCakes = cakeRepository.saveAll(cakeMapper.mapToEntity(createCakeRequest.getCakes()));
        return CreateCakeResponse.builder().cakes(cakeMapper.mapToVo(savedCakes)).build();
    }
}
