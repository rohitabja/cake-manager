package com.waracle.cakemgr.service;

import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.CakeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeServiceImplTest {

    private CakeServiceImpl testee;

    @Mock
    private CakeRepository cakeRepository;

    @Mock
    private CakeMapper cakeMapper;


    @BeforeEach
    void setup() {
        testee = new CakeServiceImpl(cakeRepository, cakeMapper);
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

}