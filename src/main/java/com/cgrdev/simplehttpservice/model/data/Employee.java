package com.cgrdev.simplehttpservice.model.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Employee {

    public enum ROLE {
        juniorDev,
        seniorDev,
        softArch
    }

    private @Id @GeneratedValue Long id;
    private String name;
    private ROLE role;
    private int salary;

    Employee() {}

    Employee(String name, ROLE role) {
        this.name = name;
        this.role = role;
    }

    @PreUpdate
    @PrePersist
    public void calcSalary() {
        switch (role){
            case juniorDev:
                salary=30;
                break;
            case seniorDev:
                salary=60;
                break;
            case softArch:
                salary=100;
                break;
        }
    }
}