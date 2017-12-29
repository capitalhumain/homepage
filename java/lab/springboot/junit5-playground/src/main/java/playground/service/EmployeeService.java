package playground.service;

import playground.model.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee getEmployeeByName(String name);

    public List<Employee> findAll();
}
