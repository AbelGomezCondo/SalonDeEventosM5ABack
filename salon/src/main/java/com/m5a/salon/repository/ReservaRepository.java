/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.m5a.salon.repository;

import com.m5a.salon.model.entity.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author LaptopSA
 */
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    
   @Query("SELECT r.resFechaEvento, r.reCotiId.cotiTipoEvento, r.resEstado, r.reCotiId.cotiMonto, r.reCotiId.salId.salNombre FROM Reserva r WHERE r.usuId.usuId = :userId")
    List<Object[]> findCustomReservasByUserId(@Param("userId") Long userId);
    
}
