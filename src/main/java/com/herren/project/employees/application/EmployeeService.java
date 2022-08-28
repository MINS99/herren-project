package com.herren.project.employees.application;

import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.EmployeeRepository;
import com.herren.project.employees.dto.EmployeeInfoResponse;
import com.herren.project.employees.dto.EmployeeCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeInfoResponse createEmployee(EmployeeCreateRequest employeeCreateRequest) {
        Employee savedEmployee = employeeRepository.save(employeeCreateRequest.toEntity());
        return EmployeeInfoResponse.toEntity(savedEmployee);
    }
}
