package com.waracle.cakemgr.service;

import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.data.UserCakeRepository;
import com.waracle.cakemgr.data.UserRepository;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.entity.User;
import com.waracle.cakemgr.entity.UserCakeMapping;
import com.waracle.cakemgr.exception.CakeNotFoundException;
import com.waracle.cakemgr.exception.UserNotFoundException;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.CakeResponse;
import com.waracle.cakemgr.model.CakeVo;
import com.waracle.cakemgr.model.CreateCakeRequest;
import com.waracle.cakemgr.model.CreateCakeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class CakeServiceImpl implements CakeService {

    private final CakeRepository cakeRepository;
    private final UserCakeRepository userCakeRepository;
    private final UserRepository userRepository;
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
                        .id(cake.getCakeId())
                        .name(cake.getName())
                        .desc(cake.getDescription())
                        .image(cake.getImage())
                        .build())
                .orElseThrow(() -> new CakeNotFoundException(String.format("Cake not found for id %s", id)));
    }

    @Override
    @Transactional
    public CakeVo createCake(@NotNull final CakeVo cakeVo) {
        final Optional<User> userFound = userRepository.findByUserKey("12345");
        final Cake savedCake;
        if (userFound.isEmpty()) {
            savedCake = cakeMapper.mapToEntity(cakeVo);
            final List<Cake> cakes = new ArrayList<>();
            cakes.add(savedCake);
            userRepository.saveAndFlush(User.builder().userKey("12345").cakes(cakes).build());
        } else {
            savedCake = cakeRepository.saveAndFlush(cakeMapper.mapToEntity(cakeVo, userFound.get()));
            userCakeRepository.save(UserCakeMapping.builder().userId(userFound.get().getUserId()).cakeId(savedCake.getCakeId()).build());
        }

        return cakeMapper.mapToVo(savedCake);
    }

    @Override
    @Transactional
    public CreateCakeResponse createCakes(final CreateCakeRequest createCakeRequest) {
        final List<Cake> savedCakes = cakeRepository.saveAll(cakeMapper.mapToEntity(createCakeRequest.getCakes()));
        return CreateCakeResponse.builder().cakes(cakeMapper.mapToVo(savedCakes)).build();
    }

    @Override
    @Transactional
    public CakeResponse getCakesForUser(final String userKey) {
        final Optional<User> foundUser = userRepository.findByUserKey(userKey);
        return foundUser.map(user -> CakeResponse.builder()
                        .cakes(cakeMapper.mapToVo(user.getCakes()))
                        .build())
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found for %s", userKey)));
    }
}
