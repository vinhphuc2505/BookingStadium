package com.BookingStadium.IdentityService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    private String roleId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> userList = new ArrayList<>();
}
