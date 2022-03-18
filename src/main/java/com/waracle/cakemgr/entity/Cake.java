package com.waracle.cakemgr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAKE")
public class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAKE_ID", unique = true, nullable = false)
    private Integer cakeId;

    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @Column(name = "IMAGE", nullable = false, length = 300)
    private String image;

    @ManyToMany(mappedBy = "cakes")
    private Collection<User> users;

}