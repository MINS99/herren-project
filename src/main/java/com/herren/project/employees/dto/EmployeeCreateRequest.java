package com.herren.project.employees.dto;

import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.EmployeeStatus;
import javax.validation.constraints.NotBlank;

public class EmployeeCreateRequest {
    @NotBlank(message = "이름은 비워둘 수 없습니다.")
    private final String name;
    @NotBlank(message = "연락처는 비워둘 수 없습니다.")
    private final String phoneNumber;
    private final String kakaoId;

    public EmployeeCreateRequest(String name, String phoneNumber, String kakaoId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.kakaoId = kakaoId;
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

    public Employee toEntity() {
        return new Employee(name, phoneNumber, kakaoId, EmployeeStatus.NORMAL);
    }
}
