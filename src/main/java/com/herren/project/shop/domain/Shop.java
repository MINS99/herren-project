package com.herren.project.shop.domain;

import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.Staff;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String shopName;
    @Column(unique = true)
    private String bizNumber;
    private String phoneNumber;
    private String kakaoId;
    @Enumerated(EnumType.STRING)
    private ShopStatus shopStatus;
    @Embedded
    private Staff staff;

    protected Shop() {
    }

    public Shop(String shopName, String bizNumber, String phoneNumber, String kakaoId, ShopStatus shopStatus) {
        this.shopName = shopName;
        this.bizNumber = bizNumber;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
        this.shopStatus = shopStatus;
        this.staff = new Staff();
    }

    public Long getId() {
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

    public ShopStatus getShopStatus() {
        return shopStatus;
    }

    public List<Employee> getStaff() {
        return staff.getStaff();
    }

    public void changeShopStatus(ShopStatus shopStatus) {
        this.shopStatus = shopStatus;
        if (this.shopStatus == ShopStatus.DELETE) {
            this.bizNumber = null;
            this.phoneNumber = null;
            this.kakaoId = null;
        }
    }

    public void updateStaff(Employee employee) {
        this.staff.addEmployee(employee);
    }

    public void deleteStaffInfo(Employee employee) {
        this.staff.deleteEmployee(employee);
    }
}
