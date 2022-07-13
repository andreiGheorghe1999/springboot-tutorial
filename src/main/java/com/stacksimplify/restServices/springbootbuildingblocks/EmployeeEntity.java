package com.stacksimplify.restServices.springbootbuildingblocks;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity extends RepresentationModel {

    @Id
    @GeneratedValue
    @JsonView(Views.Normal.class)
    private Long empId;

    @NotEmpty(message = "Employee is mandatory. Please provide a name.")
    @Column(name = "EMPLOYEE_NAME", length = 50, nullable = false, unique = true)
    @JsonView(Views.Normal.class)
    private String name;

    @NotEmpty(message = "Department is mandatory. Please assign the employee a department.")
    @Column(name = "DEPARTMENT_NAME", length = 50, nullable = false, unique = true)
    @JsonView(Views.Normal.class)
    private String department;

    @Column(name = "LOGIN_TIME", length = 20, nullable = false)
    @JsonView(Views.Manager.class)
    private String loginTime;

    @Column(name = "LOGOUT_TIME", length = 20, nullable = false)
    @JsonView(Views.Manager.class)
    private String logoutTime;

    @Column(name = "SALARY", length = 50, nullable = false)
    @JsonView(Views.HR.class)
    private String salary;

    @Column(name = "LAST_PROMOTION_DATE", length = 20, nullable = false)
    @JsonView(Views.HR.class)
    private String lastPromotionDate;
}
