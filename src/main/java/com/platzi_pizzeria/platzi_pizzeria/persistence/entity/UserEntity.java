package com.platzi_pizzeria.platzi_pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "locked", nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(name = "disabled", nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;
}
