package com.cgrdev.simplehttpservice.controller;

import java.util.ArrayList;
import java.util.List;

import com.cgrdev.simplehttpservice.controller.exceptions.EmployeeNotFoundException;
import com.cgrdev.simplehttpservice.controller.exceptions.EmptyRoleException;
import com.cgrdev.simplehttpservice.model.data.Employee;
import com.cgrdev.simplehttpservice.model.data.EmployeeRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Indicates that data returned by each method will be written straight into the response body, instead of rendering a template
@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @GetMapping("/employees/role/{role}")
    List<Employee> employeesByRole(@PathVariable
                                           Employee.ROLE role) {

        List<Employee> allEmployees = repository.findAll();
        List<Employee> roleEmployees = new ArrayList<>();

        for (Employee employee :
                allEmployees) {
            if (employee.getRole().equals(role)) roleEmployees.add(employee);
        }

        if (roleEmployees.size()==0) throw new EmptyRoleException(role);
        else return roleEmployees;
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    if (newEmployee.getName()!="") employee.setName(newEmployee.getName());
                    if (newEmployee.getRole()!=null) employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {

        if (repository.existsById(id)) repository.deleteById(id);
        else throw new EmployeeNotFoundException(id);
    }
}