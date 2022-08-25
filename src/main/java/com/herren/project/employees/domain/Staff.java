package com.herren.project.employees.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Staff {
    @OneToMany(mappedBy = "shop")
    private List<Employee> staff = new ArrayList<>();

    protected Staff() {
    }

    public Staff(List<Employee> staff) {
        this.staff = new ArrayList<>(staff);
    }

    public List<Employee> getStaff() {
        return Collections.unmodifiableList(staff);
    }

    public void addEmployee(Employee employee) {
        this.staff.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.staff.remove(employee);
    }
}
