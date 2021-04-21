package com.example.abw.entities.user;

import lombok.Data;
import org.springframework.context.annotation.Bean;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull(message = "user name must not be null")
    @Column(name = "name")
    @Size(min = 2, message = "user name min size must be 2")
    private String name;

    @NotNull(message = "user email must not be null")
    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "user wrong email")
    private String email;

    @NotNull(message = "user phoneNumber must not be null")
    @Pattern(regexp = "^\\+?[34][-\\(]?\\d{4}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$", message = "user wrong phoneNumber")
    private String phoneNumber;

    @NotNull(message = "user password must not be null")
    private String password;

    @NotNull(message = "user role must not be null")
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


}
