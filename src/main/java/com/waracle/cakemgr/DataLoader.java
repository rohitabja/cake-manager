package com.waracle.cakemgr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.data.CakeRepository;
import com.waracle.cakemgr.entity.Cake;
import com.waracle.cakemgr.mapper.CakeMapper;
import com.waracle.cakemgr.model.CakeVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader {

    private final CakeRepository cakeRepository;

    private final CakeMapper cakeMapper;

    private final ObjectMapper objectMapper;

    @Value("${cake.storage.url}")
    private final String storageUrl;

    @PostConstruct
    public void storeCakes() throws IOException {
        try (final InputStream inputStream = new URL(storageUrl).openStream()) {
            log.info("Process started to store all cakes");
            final String jsonString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            final Set<CakeVo> cakeVos = objectMapper.readValue(jsonString, new TypeReference<>() {
            });
            final Collection<Cake> cakes = cakeMapper.mapToEntity(cakeVos);
            cakeRepository.saveAll(cakes);
            log.info("All cakes are stored now");
        }
    }


}
