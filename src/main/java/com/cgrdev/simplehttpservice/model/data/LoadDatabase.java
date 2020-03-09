package com.cgrdev.simplehttpservice.model.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @Slf4j just for logging, log is a Slf4j-based LoggerFactory
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Employee("Carlos Garcia", Employee.ROLE.juniorDev)));
            log.info("Preloading " + repository.save(new Employee("Dennis Ritchie", Employee.ROLE.softArch)));
            log.info("Preloading " + repository.save(new Employee("James Gosling", Employee.ROLE.seniorDev)));
            log.info("Preloading " + repository.save(new Employee("Linus Torvalds", Employee.ROLE.softArch)));
            log.info("Preloading " + repository.save(new Employee("Brian Kernighan", Employee.ROLE.seniorDev)));

        };
    }
}
