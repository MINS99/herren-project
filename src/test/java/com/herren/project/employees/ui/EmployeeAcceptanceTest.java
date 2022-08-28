package com.herren.project.employees.ui;

import static org.assertj.core.api.Assertions.assertThat;

import com.herren.project.AcceptanceTest;
import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.EmployeeStatus;
import com.herren.project.employees.dto.EmployeeCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class EmployeeAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void 직원을_등록한다() {
        Employee 직원1 = new Employee("박직원", "010-9999-9999", "kakao3@kakao.com", EmployeeStatus.NORMAL);

        ExtractableResponse<Response> response = 직원을_등록한다(직원1);

        직원_등록_성공(response);
    }

    @Test
    void 직원_등록시_필수데이터를_입력하지_않으면_오류를_반환한다() {
        Employee 직원1 = new Employee(null, "010-9999-9999", "kakao3@kakao.com", EmployeeStatus.NORMAL);

        ExtractableResponse<Response> response = 직원을_등록한다(직원1);

        직원_등록_실패(response);
    }

    public static ExtractableResponse<Response> 직원을_등록한다(Employee employee) {
        EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest(employee.getName(),
                employee.getPhoneNumber(), employee.getKakaoId());
        return AcceptanceTest.doPost("/api/v1/staff", employeeCreateRequest);
    }

    public static void 직원_등록_성공(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    public static void 직원_등록_실패(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
