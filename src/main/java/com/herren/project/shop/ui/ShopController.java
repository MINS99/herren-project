package com.herren.project.shop.ui;

import com.herren.project.shop.application.ShopService;
import com.herren.project.shop.dto.ShopCreateRequest;
import com.herren.project.shop.dto.ShopInfoResponse;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopInfoResponse> findShopInfo(@PathVariable Long id) {
        ShopInfoResponse shopInfoResponse = shopService.findShopInfo(id);
        return ResponseEntity.ok().body(shopInfoResponse);
    }

    @PostMapping
    public ResponseEntity<Void> createShopInfo(@RequestBody @Valid ShopCreateRequest shopCreateRequest) {
        ShopInfoResponse shopInfoResponse = shopService.createShopInfo(shopCreateRequest);
        return ResponseEntity.created(URI.create("/api/v1/shops/" + shopInfoResponse.getId())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShopInfo(@PathVariable Long id) {
        shopService.deleteShopInfo(id);
        return ResponseEntity.noContent().build();
    }
}
