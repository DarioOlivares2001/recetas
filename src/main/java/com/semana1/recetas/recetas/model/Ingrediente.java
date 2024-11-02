package com.semana1.recetas.recetas.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "INGREDIENTES")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "cantidad")
    private String cantidad; // Puede ser String para manejar unidades

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }

    public Receta getReceta() { return receta; }
    public void setReceta(Receta receta) { this.receta = receta; }
}
