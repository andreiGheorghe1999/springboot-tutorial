package com.stacksimplify.restServices.springbootbuildingblocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // marks a repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Important to see the return type in order to know how we handle the response
    // We can filter by any field in the table in a much more interactive manner (and understandable)
    // Reusable pieces of code
    UserEntity findByUsername(String username);
}
