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

import com.bad.melody.model.Cancion;
import com.bad.melody.model.ComentarioCancion;
import com.bad.melody.model.Usuario;
import com.bad.melody.services.impl.CancionServiceImpl;
import com.bad.melody.services.impl.ComentarioCancionServiceImpl;
import com.bad.melody.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/comentarioCanciones")
public class ComentarioCancionController {

    @Autowired
    ComentarioCancionServiceImpl comentarioCancionServiceImpl;

    @Autowired
    CancionServiceImpl cancionServiceImpl;

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;
    
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
        comentarioCancionServiceImpl.calificacionCancion(
            comentarioCancion.getCancion(),
            comentarioCancion.getUsuario(),
            comentarioCancion.getCalificacion()
        );
        return ResponseEntity.ok("Calificación registrada con éxito");}

        @PostMapping("/{cancionId}/calificar")
        public ResponseEntity<List<ComentarioCancion>> calificarCancion( @PathVariable Long cancionId, @RequestParam Long usuarioId, @RequestParam int calificacion,@RequestParam String comentario) {
    
            try {
            Cancion cancion = cancionServiceImpl.obtenerCancionPorId(cancionId);
            Usuario usuario = usuarioServiceImpl.obtenerUsuarioPorId(usuarioId);

                List<ComentarioCancion> resultado = comentarioCancionServiceImpl.calificar(cancion, usuario, calificacion, comentario);
                return ResponseEntity.ok(resultado);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }
    }

