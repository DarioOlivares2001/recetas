package com.semana1.recetas.recetas.repository;

import com.semana1.recetas.recetas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    Optional<Receta> findByNombreIgnoreCase(String nombre); // Método para buscar receta por nombre
}
