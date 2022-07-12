/**
 * Class name: UserEntity.java
 * Copyright (c)2022, GMV
 * All rights reserved.
 *
 * @author angh
 * Project acronym: MPSSERV
 * Module name : springboot-tutorial
 * Java compiler : jdk/ojdk 1.8
 * Description:
 */
package com.stacksimplify.restServices.springbootbuildingblocks;


import org.springframework.hateoas.RepresentationModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

// JPA-- reprezentare in memorie (la un refresh sau restart al aplicatiei, toate datele sunt sterse)
// prin urmare se utilizeaza DB H2 -- pentru persistenta datelor in momentul restartarii, inserari de date la runtime etc.

/**
 * Class name: UserEntity.java Copyright (c)2022, GMV All rights reserved.
 *
 * @author angh Project acronym: MPSSERV Type name : springboot-tutorial Java compiler : jdk/ojdk
 *         1.8 Description:
 */
@Entity // notatie utilizata cu scopul de reprezentare a fiecarei instante sub forma tabelara
// (mapare sub forma tabelara)
// Numele entitatii by default este asociat cu numele clasei, insa aceasta se poate
// suprascrie utilizand
// parametrul name . Ex : @Entity(name="User")
@Table(name = "SPRINGBOOT_USERS") // defineste modul in care este salvata tabela la nivelul bazei de date
// Ex : @Table(name="User") -- tabela este salvata cu numele "User"
// Un alt parametru pe care il putem utiliza este "schema" astfel ca
// acest parametru este responsabil pentru diferentierea a 2 tabele din 2 aplicatii diferite
// care ruleaza in acelasi timp,
// dar au acelasi nume
public class UserEntity extends RepresentationModel {
    @Id // responsabila pentru a marca cheia primara
    @GeneratedValue // putem defini strategia de generare a cheii primare (din 4 posibile)
    private Long userId;
    @NotEmpty(message = "Username is mandatory. Please provide an username.")
    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true) // defineste modul in
    // care este stocata
    // coloana la momentul
    // maparii in BD
    // length = 50 ; --- restrictie de maxim 50 caractere
    // nullable = false --- nu pot fi NULL values
    // unique -- poate impune restrictie de unicitate a valorilor
    private String username;
    @Size(min = 2, message = "Firstname should have at least 2 characters.")
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;
    @Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
    private String email;
    @Column(name = "USER_ROLE", length = 50, nullable = false)
    private String role;

    @Column(name = "USER_SSN", length = 50, nullable = false, unique = true)
    private String ssn;


    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    // No argument constructor
    public UserEntity() {

    }

    /**
     * Constructor using fields
     *
     * @param id
     * @param username
     * @param firstName
     * @param lastName
     * @param email
     * @param role
     * @param ssn
     */
    public UserEntity(Long id,
                      String username,
                      String firstName,
                      String lastName,
                      String email,
                      String role,
                      String ssn) {
        super();
        this.userId = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserEntity [id=" + userId
                + ", username="
                + username
                + ", firstName="
                + firstName
                + ", lastName="
                + lastName
                + ", email="
                + email
                + ", role="
                + role
                + ", ssn="
                + ssn
                + "]";
    }

    /**
     * @return the id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param id
     *          the id to set
     */
    public void setUserId(Long id) {
        this.userId = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *          the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *          the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *          the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *          the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *          the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn
     *          the ssn to set
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

}
