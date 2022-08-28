package com.herren.project.employees.domain;

import com.herren.project.exception.CommonException;
import com.herren.project.exception.ErrorCode;
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
            throw new CommonException(ErrorCode.DUPLICATE_SHOP_INFO);
        }
    }
}
