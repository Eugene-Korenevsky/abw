package com.example.abw.entities.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Size(min = 2, message = "name min size must be 2")
    private String name;

    @NotNull
    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "wrong email")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\\\+?[0-9\\\\-\\\\s]*$", message = "wrong phoneNumber")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,19}$",
            message = "password must have more than 3 characters but less than 20." +
                    "Must contain only alphanumeric characters.Must contain letters and numbers.")
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


}
