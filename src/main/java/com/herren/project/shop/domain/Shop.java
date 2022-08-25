package com.herren.project.shop.domain;

import com.herren.project.staff.domain.Staff;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String shopName;
    @Column(nullable = false, unique = true)
    private String bizNumber;
    @Column(nullable = false)
    private String phoneNumber;
    private String kakaoId;
    @Enumerated(EnumType.STRING)
    private ShopStatus shopStatus;
    @OneToMany(mappedBy = "shop")
    private List<Staff> staffList;

    protected Shop() {
    }

    public Shop(String shopName, String bizNumber, String phoneNumber, String kakaoId,
                ShopStatus shopStatus, List<Staff> staffList) {
        this.shopName = shopName;
        this.bizNumber = bizNumber;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
        this.shopStatus = shopStatus;
        this.staffList = staffList;
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

    public ShopStatus getShopStatus() {
        return shopStatus;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void changeShopStatus(ShopStatus shopStatus) {
        this.shopStatus = shopStatus;
        if (this.shopStatus == ShopStatus.DELETE) {
            this.bizNumber = null;
            this.phoneNumber = null;
            this.kakaoId = null;
        }
    }
}
