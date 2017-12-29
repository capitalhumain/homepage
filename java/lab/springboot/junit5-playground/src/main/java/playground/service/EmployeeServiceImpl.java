package playground.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import playground.model.Employee;
import playground.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


//    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

}
