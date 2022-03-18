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
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Integer userId;

    @Column(name = "USER_KEY", unique = true, nullable = false)
    private String userKey;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_CAKE_MAPPING",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CAKE_ID")
    )
    private Collection<Cake> cakes;

}
