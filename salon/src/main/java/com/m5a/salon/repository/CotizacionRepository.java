/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.m5a.salon.repository;

import com.m5a.salon.model.entity.Cotizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author LaptopSA
 */
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {

    @Query("SELECT c.salId.salNombre AS salonNombre, c.cotiMonto AS monto, c.cotiFechaEvento AS fechaReserva "
            + "FROM Cotizacion c "
            + "WHERE c.usuId.usuId = :usuarioId")
    List<Object[]> findCotizacionesByUsuarioId(@Param("usuarioId") Long usuarioId);
}
