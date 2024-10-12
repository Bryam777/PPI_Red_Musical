package com.bad.melody.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bad.melody.model.ComentarioCancion;
import com.bad.melody.services.impl.ComentarioCancionServiceImpl;

@RestController
@RequestMapping("/api/comentarioCanciones")
public class ComentarioCancionController {

    @Autowired
    ComentarioCancionServiceImpl comentarioCancionServiceImpl;
    
    @PostMapping("/ingresar-comentario")
    public ResponseEntity <ComentarioCancion> nuevoComentario(@RequestParam Long cancionId,@RequestParam Long usuarioId, @RequestParam String comentario ){
        try {
            ComentarioCancion nuevoComentarioCancion = comentarioCancionServiceImpl.dejarComentario(cancionId, usuarioId, comentario);
            return ResponseEntity.ok(nuevoComentarioCancion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
    }

    @GetMapping("/cancion/{cancionId}")
    public ResponseEntity<List<ComentarioCancion>> obtenerComentariosPorCancion(@PathVariable Long cancionId) {
        List<ComentarioCancion> comentarios = comentarioCancionServiceImpl.obtenerComentarioPorCancion(cancionId);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("promedio-calificacion/{id}")
    public ResponseEntity<Long> pormidioCalificaacion (@PathVariable Long id){
        long promedio = comentarioCancionServiceImpl.obtenerPromedioCalificacion(id);
        return ResponseEntity.ok(promedio);
    }

    @PostMapping("/calificar")
    public ResponseEntity<String> calificarCancion(@RequestBody ComentarioCancion comentarioCancion) {
        // Llamamos al servicio para calificar la canción
        comentarioCancionServiceImpl.calificacionCancion(
            comentarioCancion.getCancion(),
            comentarioCancion.getUsuario(),
            comentarioCancion.getCalificacion()
        );
        return ResponseEntity.ok("Calificación registrada con éxito");
    }
}
