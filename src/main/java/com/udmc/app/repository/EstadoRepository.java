package com.udmc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udmc.app.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
}