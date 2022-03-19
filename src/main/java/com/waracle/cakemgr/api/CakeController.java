package com.waracle.cakemgr.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.CakeResponse;
import com.waracle.cakemgr.model.CakeVo;
import com.waracle.cakemgr.model.CreateCakeRequest;
import com.waracle.cakemgr.model.CreateCakeResponse;
import com.waracle.cakemgr.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class CakeController {

    private final CakeService cakeService;
    private final ObjectMapper objectMapper;

    //TODO Currently this api downloads all cakes from the repository. It should consider current logged in user.
    @GetMapping("cakes")
    public CakeResponse getAllCakes() {
        return cakeService.getAllCakes();
    }

    @GetMapping("download-cakes")
    public ResponseEntity<byte[]> downloadCakes() throws JsonProcessingException {
        final CakeResponse allCakes = cakeService.getAllCakes();
        final byte[] bytes = objectMapper.writeValueAsBytes(allCakes);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=cakes.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(bytes.length)
                .body(bytes);
    }

    //TODO This api is not fully implemented to use logged in user details to map the cakes stored in the system.
    @PostMapping("cakes")
    public CreateCakeResponse createCakes(@RequestBody final CreateCakeRequest createCakeRequest) {
        return cakeService.createCakes(createCakeRequest);
    }

    @PostMapping("cake")
    public CakeVo createCake(@RequestBody final CakeVo cake, @AuthenticationPrincipal OAuth2User principal) {
        final String userLogin = principal.getAttribute("login");
        return cakeService.createCake(cake, userLogin);
    }

    @GetMapping("cake/{id}")
    public CakeVo getCakeForId(@PathVariable("id") final int id) {
        return cakeService.getCakeForId(id);
    }

}
