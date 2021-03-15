package com.example.abw.entities.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Data
@Table(name = "user_info")
public class User implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "wrong email")
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


}
