package com.waracle.cakemgr.mapper;

import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.model.CakeVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CakeMapperImplTest {

    private CakeMapperImpl testee;

    @BeforeEach
    void setup() {
        testee = new CakeMapperImpl();
    }

    @Test
    void shouldMapASingleCakeToEntity() {
        final Cake actual = testee.mapToEntity(CakeVo.builder()
                .id(99)
                .name("My Cake")
                .desc("My delicious cake")
                .image("abc.com")
                .build());

        assertThat(actual).isNotNull();
        assertThat(actual.getCakeId()).isEqualTo(99);
        assertThat(actual.getName()).isEqualTo("My Cake");
        assertThat(actual.getDescription()).isEqualTo("My delicious cake");
        assertThat(actual.getImage()).isEqualTo("abc.com");
    }

    @Test
    void shouldMapCakesToEntities() {
        final List<CakeVo> cakes = new ArrayList<>();
        cakes.add(CakeVo.builder()
                .id(99)
                .name("My 99 Cake")
                .desc("My delicious cake")
                .image("abc.com")
                .build());

        cakes.add(CakeVo.builder()
                .id(100)
                .name("My 100 Cake")
                .desc("My most delicious cake")
                .image("xyz.com")
                .build());

        final Collection<Cake> actual = testee.mapToEntity(cakes);

        final List<Cake> expected = new ArrayList<>();
        expected.add(Cake.builder().cakeId(99)
                .name("My 99 Cake")
                .description("My delicious cake")
                .image("abc.com").build());
        expected.add(Cake.builder().cakeId(100)
                .name("My 100 Cake")
                .description("My most delicious cake")
                .image("xyz.com").build());

        assertThat(actual).hasSize(2);
        assertThat(actual).containsAll(expected);
    }

    @Test
    void shouldMapNullCakesToEmptyEntities() {
        final Collection<Cake> actual = testee.mapToEntity((Collection<CakeVo>) null);

        assertThat(actual).hasSize(0);
    }

    @Test
    void shouldMapEmptyCakesToEmptyEntities() {
        final Collection<Cake> actual = testee.mapToEntity(Collections.emptyList());

        assertThat(actual).hasSize(0);
    }
}