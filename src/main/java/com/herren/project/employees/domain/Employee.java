package com.herren.project.employees.domain;

import com.herren.project.exception.CommonException;
import com.herren.project.exception.ErrorCode;
import com.herren.project.shop.domain.Shop;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String phoneNumber;
    private String kakaoId;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false)
    private Shop shop;

    protected Employee() {
    }

    public Employee(String name, String phoneNumber, String kakaoId, EmployeeStatus employeeStatus) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
        this.employeeStatus = employeeStatus;
    }

    public Long getId() {
        return id;
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

    public EmployeeStatus getStaffStatus() {
        return employeeStatus;
    }

    public Shop getShop() {
        return shop;
    }

    public void joinShop(Shop shop) {
        validateJoinShopCheck();
        this.shop = shop;
        this.shop.updateStaff(this);
    }

    public void resignShop() {
        if (this.shop != null) {
            this.shop.deleteStaffInfo(this);
            this.shop = null;
        }
    }

    public void changeStaffStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
        if (this.employeeStatus == EmployeeStatus.DELETE) {
            this.name = null;
            this.phoneNumber = null;
            this.kakaoId = null;
            resignShop();
        }
    }

    private void validateJoinShopCheck() {
        if (this.shop != null) {
            throw new CommonException(ErrorCode.DUPLICATE_SHOP_INFO);
        }
    }
}
