/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m5a.salon.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LaptopSA
 */
@Entity
@Data
//@AllArgsConstructor
@Table(name = "Rol")
//@NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolId")
    private int rolId;

    @Column(name = "rolNombre")
    private String rolNombre;

    @Column(name = "rolFechaRegistro")
    private Timestamp rolFechaRegistro;

    @JsonIgnore
    @OneToMany(mappedBy = "rol")
    private List<Usuario> listUsuarios;

}
        