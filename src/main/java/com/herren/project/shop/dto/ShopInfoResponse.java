package com.herren.project.shop.dto;

import com.herren.project.employees.dto.EmployeeInfoResponse;
import com.herren.project.shop.domain.Shop;
import java.util.List;
import java.util.stream.Collectors;

public class ShopInfoResponse {
    private Long id;
    private String shopName;
    private String bizNumber;
    private String phoneNumber;
    private String kakaoId;
    private List<EmployeeInfoResponse> staff;

    public ShopInfoResponse() {
    }

    private ShopInfoResponse(Long id, String shopName, String bizNumber, String phoneNumber, String kakaoId,
                             List<EmployeeInfoResponse> staff) {
        this.id = id;
        this.shopName = shopName;
        this.bizNumber = bizNumber;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
        this.staff = staff;
    }

    public long getId() {
        return id;
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

    public List<EmployeeInfoResponse> getStaff() {
        return staff;
    }

    public static ShopInfoResponse toEntity(Shop shop) {
        List<EmployeeInfoResponse> employeeInfoResponses
                = shop.getStaff().stream().map(EmployeeInfoResponse::toEntity).collect(Collectors.toList());
        return new ShopInfoResponse(shop.getId(), shop.getShopName(), shop.getBizNumber(), shop.getPhoneNumber(),
                shop.getKakaoId(), employeeInfoResponses);
    }
}
