package com.herren.project.staff.domain;

import com.herren.project.shop.domain.Shop;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private String kakaoId;
    @Enumerated(EnumType.STRING)
    private StaffStatus staffStatus;
    @ManyToOne
    private Shop shop;

    protected Staff() {
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public Shop getShop() {
        return shop;
    }
}
