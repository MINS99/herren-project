package com.herren.project.shop.domain;

import com.herren.project.staff.domain.Staff;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @OneToMany(mappedBy = "shop")
    private List<Staff> staffList;

    protected Shop() {
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

    public List<Staff> getStaffList() {
        return staffList;
    }
}
