package com.waracle.cakemgr.api;

import com.waracle.cakemgr.model.CakeVo;
import com.waracle.cakemgr.model.CakeResponse;
import com.waracle.cakemgr.model.CreateCakeRequest;
import com.waracle.cakemgr.model.CreateCakeResponse;
import com.waracle.cakemgr.service.CakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class CakeController {

    private final CakeService cakeService;

    @GetMapping("cakes")
    public CakeResponse getAllCakes() {
        return cakeService.getAllCakes();
    }

    @GetMapping("cake/{id}")
    public CakeVo getCakeForId(@PathVariable("id") final int id) {
        return cakeService.getCakeForId(id);
    }

    @PostMapping("cake")
    public CakeVo createCake(@RequestBody final CakeVo cake) {
        return cakeService.createCake(cake);
    }

    @PostMapping("cakes")
    public CreateCakeResponse createCakes(@RequestBody final CreateCakeRequest createCakeRequest) {
        return cakeService.createCakes(createCakeRequest);
    }

    @GetMapping("cakes/current-user")
    public Collection<CakeVo> getAllCakesForCurrentUser() {
        throw new UnsupportedOperationException("This api is not supported yet");
    }
}
