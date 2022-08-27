package com.herren.project.employees.dto;

import com.herren.project.employees.domain.Employee;

public class EmployeeInfoResponse {
    private final Long id;
    private final String name;
    private final String phoneNumber;
    private final String kakaoId;

    private EmployeeInfoResponse(Long id, String name, String phoneNumber, String kakaoId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
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

    public static EmployeeInfoResponse toEntity(Employee employee) {
        return new EmployeeInfoResponse(employee.getId(), employee.getName(), employee.getPhoneNumber(),
                employee.getKakaoId());
    }
}
