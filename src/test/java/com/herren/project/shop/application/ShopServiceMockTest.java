package com.herren.project.shop.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

import com.herren.project.exception.CommonException;
import com.herren.project.shop.domain.Shop;
import com.herren.project.shop.domain.ShopRepository;
import com.herren.project.shop.domain.ShopStatus;
import com.herren.project.shop.dto.ShopInfoResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShopServiceMockTest {

    @InjectMocks
    private ShopService shopService;

    @Mock
    private ShopRepository shopRepository;

    @Test
    @DisplayName("샵을 조회한다")
    void findShopInfo() {
        Shop shop = new Shop("헤어샵", "12345", "010-1234-1234", "kakaoid", ShopStatus.WAITING);
        given(shopRepository.findById(1L)).willReturn(Optional.of(shop));

        ShopInfoResponse shopInfoResponse = shopService.findShopInfo(1L);

        assertAll(
                () -> assertThat(shopInfoResponse.getShopName()).isEqualTo(shop.getShopName()),
                () -> assertThat(shopInfoResponse.getBizNumber()).isEqualTo(shop.getBizNumber()),
                () -> assertThat(shopInfoResponse.getPhoneNumber()).isEqualTo(shop.getPhoneNumber()),
                () -> assertThat(shopInfoResponse.getKakaoId()).isEqualTo(shop.getKakaoId())
        );
    }

    @Test
    @DisplayName("존재하지 않는 샵을 조회하면 오류를 반환한다")
    void findShopInfo_notfound() {
        assertThatThrownBy(
                () -> shopService.findShopInfo(1L)
        ).isInstanceOf(CommonException.class);
    }
}
