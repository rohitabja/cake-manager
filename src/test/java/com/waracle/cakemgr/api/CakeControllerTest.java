package com.waracle.cakemgr.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.CakeResponse;
import com.waracle.cakemgr.model.CakeVo;
import com.waracle.cakemgr.model.CreateCakeRequest;
import com.waracle.cakemgr.service.CakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeControllerTest {

    private CakeController testee;

    @Mock
    private CakeService cakeService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private CakeResponse mockCakeResponse;

    @Mock
    private CreateCakeRequest mockCreateCakeRequest;

    @Mock
    private CakeVo mockCakeVo;

    @Mock
    private OAuth2User mockUser;

    @BeforeEach
    void setup() {
        testee = new CakeController(cakeService, objectMapper);
    }

    @Test
    void shouldGetAllCakesSuccessfully() {
        testee.getAllCakes();

        verify(cakeService).getAllCakes();
    }

    @Test
    void shouldDownloadCakesSuccessfully() throws JsonProcessingException {
        final byte[] bytes = "Test".getBytes();
        when(cakeService.getAllCakes()).thenReturn(mockCakeResponse);
        when(objectMapper.writeValueAsBytes(mockCakeResponse)).thenReturn(bytes);

        final ResponseEntity<byte[]> actual = testee.downloadCakes();

        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).isEqualTo(bytes);
    }

    @Test
    void shouldCreateCakesSuccessfully() {
        testee.createCakes(mockCreateCakeRequest);

        verify(cakeService).createCakes(mockCreateCakeRequest);
    }

    @Test
    void shouldCreateCakeSuccessfully() {
        when(mockUser.getAttribute("login")).thenReturn("ABC");

        testee.createCake(mockCakeVo, mockUser);

        verify(cakeService).createCake(mockCakeVo, "ABC");
    }

    @Test
    void shouldGetCakeForIdSuccessfully() {
        testee.getCakeForId(1);

        verify(cakeService).getCakeForId(1);
    }
}