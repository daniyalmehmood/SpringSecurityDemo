package com.codearchitects.springsecurity.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(updatable = false)
    private Date createdDate;

    private Date updatedDate;

    private Boolean isActive;
}
