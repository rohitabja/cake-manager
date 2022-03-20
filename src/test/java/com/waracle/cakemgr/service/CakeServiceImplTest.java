package com.waracle.cakemgr.service;

import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.data.UserCakeRepository;
import com.waracle.cakemgr.data.UserRepository;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.entity.User;
import com.waracle.cakemgr.exception.CakeNotFoundException;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.CakeResponse;
import com.waracle.cakemgr.model.CakeVo;
import com.waracle.cakemgr.model.CreateCakeRequest;
import com.waracle.cakemgr.model.CreateCakeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeServiceImplTest {

    private CakeServiceImpl testee;

    @Mock
    private CakeRepository cakeRepository;

    @Mock
    private CakeMapper cakeMapper;

    @Mock
    private UserCakeRepository userCakeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateCakeRequest mockCreateCakeRequest;

    @BeforeEach
    void setup() {
        testee = new CakeServiceImpl(cakeRepository, userCakeRepository, userRepository, cakeMapper);
    }

    @Test
    void shouldGetAllCakes() {
        final List<Cake> cakes = new ArrayList<>();
        when(cakeRepository.findAll()).thenReturn(cakes);

        final CakeResponse actual = testee.getAllCakes();

        assertThat(actual).isNotNull();
        verify(cakeMapper).mapToVo(cakes);
    }

    @Test
    void shouldGetAllCakesThrowsException() {
        when(cakeRepository.findAll()).thenThrow(new RuntimeException("Cake Repository failed"));

        assertThrows(RuntimeException.class, () -> testee.getAllCakes());
    }

    @Test
    void shouldThrowCakeNotFoundExceptionWhenCakeIsNotFoundInRepository() {
        when(cakeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(CakeNotFoundException.class, () -> testee.getCakeForId(1));
    }

    @Test
    void shouldGetCakeForIdSuccessfully() {
        final Cake cake = Cake.builder()
                .cakeId(1)
                .name("my cake")
                .description("my desc")
                .image("my image")
                .build();

        when(cakeRepository.findById(1)).thenReturn(Optional.of(cake));

        final CakeVo actual = testee.getCakeForId(1);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getName()).isEqualTo("my cake");
        assertThat(actual.getDesc()).isEqualTo("my desc");
        assertThat(actual.getImage()).isEqualTo("my image");
    }

    @Test
    void shouldCreateCakesSuccessfully() {
        final List<CakeVo> cakesVos = new ArrayList<>();
        final List<CakeVo> expectedCakes = new ArrayList<>();
        final List<Cake> cakes = new ArrayList<>();
        final List<Cake> savedCakes = new ArrayList<>();

        when(mockCreateCakeRequest.getCakes()).thenReturn(cakesVos);
        when(cakeMapper.mapToEntity(mockCreateCakeRequest.getCakes())).thenReturn(cakes);
        when(cakeRepository.saveAll(cakes)).thenReturn(savedCakes);
        when(cakeMapper.mapToVo(savedCakes)).thenReturn(expectedCakes);

        final CreateCakeResponse actual = testee.createCakes(mockCreateCakeRequest);

        assertThat(actual).isNotNull();
        assertThat(actual.getCakes()).isEqualTo(expectedCakes);
    }

    @Test
    void shouldCreateCakeSuccessfullyWhenUserNotFoundInSystem() {
        final Optional<User> user = Optional.empty();
        final CakeVo request = CakeVo.builder().build();
        final Cake cakeEntity = Cake.builder().build();

        when(userRepository.findByUserKey("login-1")).thenReturn(user);
        when(cakeMapper.mapToEntity(request)).thenReturn(cakeEntity);

        testee.createCake(request, "login-1");

        verify(userRepository).saveAndFlush(
                argThat(arg -> arg.getUserKey().equals("login-1")
                        && arg.getCakes().contains(cakeEntity)));
        verify(cakeMapper).mapToVo(cakeEntity);
    }

    @Test
    void shouldCreateCakeSuccessfullyWhenUserLoginIsNull() {
        final CakeVo request = CakeVo.builder().build();
        final Cake cakeEntity = Cake.builder().build();

        when(cakeMapper.mapToEntity(request)).thenReturn(cakeEntity);

        testee.createCake(request, null);

        verify(userRepository).saveAndFlush(
                argThat(arg -> !StringUtils.hasText(arg.getUserKey())
                        && arg.getCakes().contains(cakeEntity)));
        verify(cakeMapper).mapToVo(cakeEntity);
    }

    @Test
    void shouldCreateCakeSuccessfullyWhenUserLoginIsEmpty() {
        final CakeVo request = CakeVo.builder().build();
        final Cake cakeEntity = Cake.builder().build();

        when(cakeMapper.mapToEntity(request)).thenReturn(cakeEntity);

        testee.createCake(request, "");

        verify(userRepository).saveAndFlush(
                argThat(arg -> !StringUtils.hasText(arg.getUserKey())
                        && arg.getCakes().contains(cakeEntity)));
        verify(cakeMapper).mapToVo(cakeEntity);
    }

    @Test
    void shouldCreateCakeSuccessfullyWhenUserLoginFoundInSystem() {
        final Optional<User> user = Optional.of(User.builder().userId(1).build());
        final CakeVo request = CakeVo.builder().build();
        final Cake cakeEntity = Cake.builder().cakeId(2).build();

        when(userRepository.findByUserKey("login-1")).thenReturn(user);
        when(cakeMapper.mapToEntity(request, user.get())).thenReturn(cakeEntity);
        when(cakeRepository.saveAndFlush(cakeEntity)).thenReturn(cakeEntity);

        testee.createCake(request, "login-1");

        verify(userCakeRepository).save(
                argThat(arg -> arg.getUserId() == 1
                        && arg.getCakeId() == 2));
        verify(cakeMapper).mapToVo(cakeEntity);
    }

}