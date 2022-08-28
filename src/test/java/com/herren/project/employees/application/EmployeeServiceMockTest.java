package com.herren.project.employees.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.EmployeeRepository;
import com.herren.project.employees.dto.EmployeeCreateRequest;
import com.herren.project.employees.dto.EmployeeInfoResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceMockTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    @DisplayName("필수, 선택 정보를 받아서 직원을 새로 등록한다")
    void createEmployee() {
        EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest("한직원", "010-2323-3232",
                "one@kakao.com");
        Employee employee = employeeCreateRequest.toEntity();
        given(employeeRepository.save(any())).willReturn(employee);

        EmployeeInfoResponse actual = employeeService.createEmployee(employeeCreateRequest);

        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("선택 정보가 없는 경우에도 직원 등록이 가능하다")
    void createEmployee_selective() {
        EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest("한직원", "010-2323-3232", null);
        Employee employee = employeeCreateRequest.toEntity();
        given(employeeRepository.save(any())).willReturn(employee);

        EmployeeInfoResponse actual = employeeService.createEmployee(employeeCreateRequest);

        assertThat(actual).isNotNull();
    }

}
