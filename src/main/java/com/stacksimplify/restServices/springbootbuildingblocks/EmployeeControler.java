package com.stacksimplify.restServices.springbootbuildingblocks;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/employees")
public class EmployeeControler {

    @Autowired
    EmployeeRepository employeeRepository;

    @JsonView(Views.Normal.class)
    @GetMapping("/normal/{id}")
    public Optional<EmployeeEntity> getNormalEmployee(@PathVariable("id") @Min(1) Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        return employee;
    }

    @JsonView(Views.Manager.class)
    @GetMapping("/manager/{id}")
    public Optional<EmployeeEntity> getManagerEmployee(@PathVariable("id") @Min(1) Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        return employee;
    }

    @JsonView(Views.HR.class)
    @GetMapping("/HR/{id}")
    public Optional<EmployeeEntity> getHREmployee(@PathVariable("id") @Min(1) Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        return employee;
    }
}

