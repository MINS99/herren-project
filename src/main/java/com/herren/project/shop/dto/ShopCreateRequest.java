package com.herren.project.shop.dto;

import com.herren.project.shop.domain.Shop;
import com.herren.project.shop.domain.ShopStatus;
import javax.validation.constraints.NotBlank;

public class ShopCreateRequest {
    @NotBlank(message = "상호명은 비워둘 수 없습니다.")
    private final String shopName;
    @NotBlank(message = "사업자번호는 비워둘 수 없습니다.")
    private final String bizNumber;
    @NotBlank(message = "연락처는 비워둘 수 없습니다.")
    private final String phoneNumber;
    private final String kakaoId;

    public ShopCreateRequest(String shopName, String bizNumber, String phoneNumber, String kakaoId) {
        this.shopName = shopName;
        this.bizNumber = bizNumber;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getBizNumber() {
        return bizNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public Shop toEntity() {
        return new Shop(this.shopName, this.bizNumber, this.phoneNumber, this.kakaoId, ShopStatus.WAITING);
    }
}
