package com.herren.project.employees.domain;

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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private String kakaoId;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
    @ManyToOne
    private Shop shop;

    protected Employee() {
    }

    public Employee(String name, String phoneNumber, String kakaoId, EmployeeStatus employeeStatus, Shop shop) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
        this.employeeStatus = employeeStatus;
        this.shop = shop;
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

    public void changeStaffStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
        if (this.employeeStatus == EmployeeStatus.DELETE) {
            this.name = null;
            this.phoneNumber = null;
            this.kakaoId = null;
            this.shop.deleteStaffInfo(this);
        }
    }
}
