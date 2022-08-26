package com.herren.project.employees.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Staff {
    @OneToMany(mappedBy = "shop")
    private final List<Employee> staff;

    public Staff() {
        this.staff = new ArrayList<>();
    }

    public List<Employee> getStaff() {
        return Collections.unmodifiableList(staff);
    }

    public void addEmployee(Employee employee) {
        validateStaffCheck(employee);
        this.staff.add(employee);
    }

    public void deleteEmployee(Employee employee) {
        this.staff.remove(employee);
    }

    private void validateStaffCheck(Employee employee) {
        if (this.staff.contains(employee)) {
            throw new IllegalArgumentException("이미 해당 샵에 존재하는 직원입니다.");
        }
    }
}
