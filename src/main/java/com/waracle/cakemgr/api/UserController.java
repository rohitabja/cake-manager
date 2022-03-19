package com.waracle.cakemgr.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        final Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("login", principal.getAttribute("login"));
        userInfo.put("id", principal.getAttribute("id"));
        userInfo.put("avatar_url", principal.getAttribute("avatar_url"));
        userInfo.put("node_id", principal.getAttribute("node_id"));

        return userInfo;
    }

}
