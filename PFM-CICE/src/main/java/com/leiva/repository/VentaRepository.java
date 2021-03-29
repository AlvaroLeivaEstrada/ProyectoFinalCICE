package com.leiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leiva.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
