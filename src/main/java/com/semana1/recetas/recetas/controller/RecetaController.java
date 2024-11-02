package com.semana1.recetas.recetas.controller;

import com.semana1.recetas.recetas.model.Receta;
import com.semana1.recetas.recetas.repository.RecetaRepository; // Importa el repositorio
import org.springframework.beans.factory.annotation.Autowired; // Importa para inyectar el repositorio
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RecetaController {

    private final RecetaRepository recetaRepository; // Repositorio de recetas

   
    public RecetaController(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository; // Inicializa el repositorio
    }

    // Permitir que todos vean las recetas
    @GetMapping("/")
    public String inicio(Model model) {
        List<Receta> recetas = recetaRepository.findAll(); // Obtener todas las recetas de la base de datos
        model.addAttribute("recetas", recetas);
        return "inicio"; // Regresa a la plantilla inicio.html
    }

    // Mostrar los detalles de una receta específica
    @GetMapping("/receta/{nombre}")
    public String detalleReceta(@PathVariable("nombre") String nombre, Model model) {
        Optional<Receta> recetaOpt = recetaRepository.findByNombreIgnoreCase(nombre); // Buscar receta por nombre
        if (recetaOpt.isPresent()) {
            model.addAttribute("receta", recetaOpt.get());
            return "detalleReceta"; // Retorna a la plantilla detalleReceta.html
        } else {
            return "error"; // Retorna a una página de error si la receta no existe
        }
    }

    // Solo usuarios autenticados pueden acceder a este formulario
    @GetMapping("/recetas/agregar")
    public String mostrarFormularioAgregar() {
        return "agregarReceta"; // Muestra la página para agregar recetas
    }

    // Solo usuarios autenticados pueden enviar el formulario
    @PostMapping("/recetas/agregar")
    public String agregarReceta(@ModelAttribute Receta receta) {
        recetaRepository.save(receta); // Guarda la nueva receta en la base de datos
        return "redirect:/"; // Redirige al inicio después de agregar la receta
    }
}
