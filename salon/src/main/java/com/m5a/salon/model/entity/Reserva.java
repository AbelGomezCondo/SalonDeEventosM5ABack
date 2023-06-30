/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m5a.salon.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LaptopSA
 */
@Entity
@Data
@AllArgsConstructor
@Table(name = "Reserva")
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservaId")
    private int resId;

    @Column(name = "resEstado")
    private boolean resEstado;

    @Column(name = "resComprobante")
    private String resComprobante;

    @Column(name = "resFechaRegistro")
    private Timestamp resFechaRegistro;

    @OneToOne
    @JoinColumn(name = "reCotiId")
    private Cotizacion reCotiId;

    @Column(name = "resFechaEvento")
    private Date resFechaEvento;

    @ManyToOne
    @JoinColumn(name = "rolId", referencedColumnName = "rolId")
    private Usuario usuario;

}
