package com.bad.melody.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bad.melody.model.Cancion;
import com.bad.melody.services.impl.CancionServiceImpl;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CancionServiceImpl cancionServiceImpl;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCancionPorId (@PathVariable("id") Long id){
        boolean verificar = cancionServiceImpl.eliminarCancion(id);
        if (verificar) {
            return ResponseEntity.ok("La cancion se elimino exitosamente");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No se pudo eliminar la cancion");
        }
    }

    @GetMapping
    public List<Cancion> obtenerTodasLasCanciones(){
        return cancionServiceImpl.obtenerLasCanciones();
    }

    @PostMapping("/ingresar-cancion")
    public ResponseEntity<Cancion> ingresarCancion(@RequestBody Cancion subirCancion){
        try {
            Cancion nuevaCancion = cancionServiceImpl.guardarCancion(subirCancion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCancion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
