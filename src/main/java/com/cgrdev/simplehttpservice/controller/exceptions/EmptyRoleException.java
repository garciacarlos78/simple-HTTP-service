package com.cgrdev.simplehttpservice.controller.exceptions;

import com.cgrdev.simplehttpservice.model.data.Employee;

public class EmptyRoleException extends RuntimeException {
    public EmptyRoleException(Employee.ROLE role) {
        super("Could not find employees with role " + role.toString());
    }
}
