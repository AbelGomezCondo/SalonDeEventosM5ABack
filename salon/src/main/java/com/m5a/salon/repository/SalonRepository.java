/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.m5a.salon.repository;

import com.m5a.salon.model.entity.Salon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author LaptopSA
 */
public interface SalonRepository extends JpaRepository<Salon, Integer> {

    @Query(value = "SELECT * FROM salon WHERE sal_id = :id", nativeQuery = true)
    Salon buscarSalonPorID(@Param("id") Integer id);

    @Query(value = "SELECT * FROM `salon` WHERE `sal_estado` = 1\n"
            + "AND LOWER(`sal_nombre`) LIKE CONCAT('%',:busqueda,'%')\n"
            + "OR LOWER(`salon_direccion`) LIKE CONCAT('%',:busqueda,'%')\n"
            + "OR `sal_costo_hora` LIKE CONCAT('%',:busqueda,'%')", nativeQuery = true)
    List<Salon> buscarSal(@Param("busqueda") String busqueda);

    @Query(value = "SELECT s.* FROM salon s WHERE s.sal_estado= :est", nativeQuery = true)
    List<Salon> listarXestado(@Param("est") int est);

}
