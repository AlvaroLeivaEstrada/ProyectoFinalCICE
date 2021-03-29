package com.leiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leiva.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
