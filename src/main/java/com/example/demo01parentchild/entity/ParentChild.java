package com.example.demo01parentchild.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ParentChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private Integer parentId;

//    @OneToMany(mappedBy = "parentId" )
//    @OneToMany
    @Transient
    private List<ParentChild> children;
}
