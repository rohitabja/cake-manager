package com.waracle.cakemgr.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private UserController testee;

    @Mock
    private OAuth2User mockUser;

    @BeforeEach
    void setup() {
        testee = new UserController();
    }

    @Test
    void shouldGetUserSuccessfully() {
        when(mockUser.getAttribute("login")).thenReturn("login-1");
        when(mockUser.getAttribute("id")).thenReturn("1");
        when(mockUser.getAttribute("avatar_url")).thenReturn("abc.com");
        when(mockUser.getAttribute("node_id")).thenReturn("node-1");

        final Map<String, Object> actual = testee.user(mockUser);

        assertThat(actual.size()).isEqualTo(4);
        assertThat(actual.get("login")).isEqualTo("login-1");
        assertThat(actual.get("id")).isEqualTo("1");
        assertThat(actual.get("avatar_url")).isEqualTo("abc.com");
        assertThat(actual.get("node_id")).isEqualTo("node-1");
    }

}