/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.m5a.salon.controller;

import com.m5a.salon.model.entity.Reserva;
import com.m5a.salon.service.ReservaServiceImpl;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LaptopSA
 */
@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    public ReservaServiceImpl reservaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Reserva>> listarReservaciones() {
        return new ResponseEntity<>(reservaService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Reserva> crearReservaciones(@RequestBody Reserva r) {
        Timestamp fecha = new Timestamp(System.currentTimeMillis());
        r.setResFechaRegistro(fecha);
        return new ResponseEntity<>(reservaService.save(r), HttpStatus.CREATED);
    }
    
     @GetMapping("/listar/{userId}")
    public ResponseEntity<List<Object[]>> listarReservacionesPorUsuario(@PathVariable Long userId) {
        List<Object[]> reservas = reservaService.findCustomReservasByUserId(userId);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Reserva> actualizarReservaciones(@PathVariable Integer id, @RequestBody Reserva r) {
        Reserva reserva = reservaService.findById(id);
        if (reserva != null) {
            try {
                reserva.setResId(r.getResId());
                reserva.setResComprobante(r.getResComprobante());
                reserva.setResEstado(r.getResEstado());
                reserva.setReCotiId(r.getReCotiId());
                reserva.setUsuId(r.getUsuId());
                reserva.setResFechaRegistro(r.getResFechaRegistro());

                return new ResponseEntity<>(reservaService.save(reserva), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Reserva> elimiarReservaciones(@PathVariable Integer id) {
        reservaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
