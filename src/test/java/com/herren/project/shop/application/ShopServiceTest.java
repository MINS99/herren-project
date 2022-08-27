package com.herren.project.shop.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.herren.project.shop.domain.Shop;
import com.herren.project.shop.domain.ShopRepository;
import com.herren.project.shop.domain.ShopStatus;
import com.herren.project.shop.dto.ShopInfoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void findShopInfo() {
        Shop savedShop = shopRepository.save(new Shop("헤어샵", "12345", "010-1234-1234", "kakaoid", ShopStatus.WAITING));

        ShopInfoResponse shopInfoResponse = shopService.findShopInfo(savedShop.getId());

        assertAll(
                () -> assertThat(shopInfoResponse.getId()).isEqualTo(savedShop.getId()),
                () -> assertThat(shopInfoResponse.getShopName()).isEqualTo(savedShop.getShopName()),
                () -> assertThat(shopInfoResponse.getBizNumber()).isEqualTo(savedShop.getBizNumber()),
                () -> assertThat(shopInfoResponse.getPhoneNumber()).isEqualTo(savedShop.getPhoneNumber()),
                () -> assertThat(shopInfoResponse.getKakaoId()).isEqualTo(savedShop.getKakaoId())
        );
    }
}
