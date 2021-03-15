package com.example.abw.entities.user;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "user_role")
public class Role implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String role;
}
