package com.herren.project.employees.ui;

import com.herren.project.employees.application.EmployeeService;
import com.herren.project.employees.dto.EmployeeCreateRequest;
import com.herren.project.employees.dto.EmployeeInfoResponse;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/staff")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Void> createStaffInfo(@RequestBody @Valid EmployeeCreateRequest employeeCreateRequest) {
        EmployeeInfoResponse employee = employeeService.createEmployee(employeeCreateRequest);
        return ResponseEntity.created(URI.create("/api/v1/staff/" + employee.getId())).build();
    }
}
