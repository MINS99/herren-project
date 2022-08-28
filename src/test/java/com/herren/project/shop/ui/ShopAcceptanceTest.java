package com.herren.project.shop.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.herren.project.AcceptanceTest;
import com.herren.project.shop.domain.Shop;
import com.herren.project.shop.domain.ShopStatus;
import com.herren.project.shop.dto.ShopCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ShopAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void 등록되어_있는_샵을_조회한다() {
        Shop 샵1 = new Shop("모모헤어", "13-100", "010-1234-1234", "momo@kakao.com", ShopStatus.WAITING);
        ExtractableResponse<Response> createResponse = 샵을_등록한다(샵1);

        ExtractableResponse<Response> response = 샵의_정보를_조회한다(createResponse);

        샵_조회_성공(샵1, response);
    }

    @Test
    void 샵을_등록한다() {
        Shop 샵1 = new Shop("벨라헤어", "31-130", "010-2345-2345", "bella@kakao.com", ShopStatus.WAITING);

        ExtractableResponse<Response> response = 샵을_등록한다(샵1);

        샵_등록_성공(response);
    }

    @Test
    void 샵을_삭제한다() {
        Shop 샵1 = new Shop("벨라헤어", "31-130", "010-2345-2345", "bella@kakao.com", ShopStatus.WAITING);
        ExtractableResponse<Response> createResponse = 샵을_등록한다(샵1);

        ExtractableResponse<Response> response = 샵을_삭제한다(createResponse);

        샵_삭제_성공(response);
    }

    public static ExtractableResponse<Response> 샵을_등록한다(Shop shop) {
        ShopCreateRequest shopCreateRequest = new ShopCreateRequest(shop.getShopName(), shop.getBizNumber(), shop.getPhoneNumber(), shop.getKakaoId());
        return AcceptanceTest.doPost("/api/v1/shops", shopCreateRequest);
    }

    public static ExtractableResponse<Response> 샵의_정보를_조회한다(ExtractableResponse<Response> response) {
        return AcceptanceTest.doGet(response.header("location"));
    }

    public static ExtractableResponse<Response> 샵을_삭제한다(ExtractableResponse<Response> response) {
        return AcceptanceTest.doDelete(response.header("location"));
    }

    public static void 샵_등록_성공(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    public static void 샵_조회_성공(Shop shop, ExtractableResponse<Response> response) {
        assertAll(
                () -> assertThat(response.jsonPath().getString("shopName")).isEqualTo(shop.getShopName()),
                () -> assertThat(response.jsonPath().getString("bizNumber")).isEqualTo(shop.getBizNumber()),
                () -> assertThat(response.jsonPath().getString("phoneNumber")).isEqualTo(shop.getPhoneNumber()),
                () -> assertThat(response.jsonPath().getString("kakaoId")).isEqualTo(shop.getKakaoId())
        );
    }

    public static void 샵_삭제_성공(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
