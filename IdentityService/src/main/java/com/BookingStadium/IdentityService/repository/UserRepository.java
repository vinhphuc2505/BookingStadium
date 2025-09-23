package com.BookingStadium.IdentityService.repository;


import com.BookingStadium.IdentityService.entity.Role;
import com.BookingStadium.IdentityService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmailOrUsername(String email, String username);

    @Query(value = "SELECT * FROM users u WHERE u.username = :loginName OR u.email = :loginName", nativeQuery = true)
    Optional<User> findByUsernameOrEmail(@Param("loginName") String loginName);

    boolean existsByRole(Role role);
}
