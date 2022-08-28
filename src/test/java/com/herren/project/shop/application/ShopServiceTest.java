package com.herren.project.shop.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.EmployeeRepository;
import com.herren.project.employees.dto.EmployeeCreateRequest;
import com.herren.project.employees.dto.EmployeeInfoResponse;
import com.herren.project.exception.CommonException;
import com.herren.project.shop.domain.Shop;
import com.herren.project.shop.domain.ShopRepository;
import com.herren.project.shop.domain.ShopStatus;
import com.herren.project.shop.dto.ShopCreateRequest;
import com.herren.project.shop.dto.ShopInfoResponse;
import org.junit.jupiter.api.DisplayName;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("샵을 조회한다")
    void findShopInfo() {
        Shop savedShop = shopRepository.save(
                new Shop("리안헤어", "가-12-345", "010-1234-1234", "rian@kakao.com", ShopStatus.WAITING));

        ShopInfoResponse shopInfoResponse = shopService.findShopInfo(savedShop.getId());

        assertAll(
                () -> assertThat(shopInfoResponse.getId()).isEqualTo(savedShop.getId()),
                () -> assertThat(shopInfoResponse.getShopName()).isEqualTo(savedShop.getShopName()),
                () -> assertThat(shopInfoResponse.getBizNumber()).isEqualTo(savedShop.getBizNumber()),
                () -> assertThat(shopInfoResponse.getPhoneNumber()).isEqualTo(savedShop.getPhoneNumber()),
                () -> assertThat(shopInfoResponse.getKakaoId()).isEqualTo(savedShop.getKakaoId())
        );
    }

    @Test
    @DisplayName("필수, 선택 정보를 받아서 샵을 새로 등록한다")
    void createShopInfo() {
        ShopCreateRequest shopCreateRequest = new ShopCreateRequest("준오헤어", "102", "010-1234-1234", "juno@kakao.com");

        ShopInfoResponse shopInfoResponse = shopService.createShopInfo(shopCreateRequest);

        assertThat(shopInfoResponse).isNotNull();
    }

    @Test
    @DisplayName("샵 생성시 사업자번호가 중복되면 409 에러가 발생한다")
    void createShopInfo_dup_bizNumber() {
        ShopCreateRequest shopCreateRequest = new ShopCreateRequest("준오헤어", "102", "010-1234-1234", "juno@kakao.com");
        shopService.createShopInfo(shopCreateRequest);
        ShopCreateRequest shopCreateRequest2 = new ShopCreateRequest("준오헤어 2호점", "102", "010-3455-3455",
                "jun2o@kakao.com");

        assertThatThrownBy(
                () -> shopService.createShopInfo(shopCreateRequest2)
        ).isInstanceOf(CommonException.class);
    }

    @Test
    @DisplayName("샵을 삭제한다")
    void deleteShopInfo() {
        ShopCreateRequest shopCreateRequest = new ShopCreateRequest("준오헤어", "102", "010-1234-1234", "juno@kakao.com");
        ShopInfoResponse shopInfoResponse = shopService.createShopInfo(shopCreateRequest);

        shopService.deleteShopInfo(shopInfoResponse.getId());

        Shop shop = shopRepository.findById(shopInfoResponse.getId()).get();
        assertThat(shop.getShopStatus()).isEqualTo(ShopStatus.DELETE);
    }
}
