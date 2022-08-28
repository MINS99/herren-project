package com.herren.project.employees.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.herren.project.employees.domain.EmployeeRepository;
import com.herren.project.employees.dto.EmployeeCreateRequest;
import com.herren.project.employees.dto.EmployeeInfoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("필수, 선택 정보를 받아서 직원을 새로 등록한다")
    void createShopInfo() {
        EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest("한직원", "010-2323-3232",
                "one@kakao.com");

        EmployeeInfoResponse employeeInfoResponse = employeeService.createEmployee(employeeCreateRequest);

        assertThat(employeeInfoResponse).isNotNull();
    }
}
