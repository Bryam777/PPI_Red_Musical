package com.bad.melody.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bad.melody.model.Cancion;
import com.bad.melody.model.ComentarioCancion;
import com.bad.melody.model.Usuario;
import com.bad.melody.repository.CancionRepository;
import com.bad.melody.repository.ComentarioCancionRepository;
import com.bad.melody.repository.UsuarioRepository;
import com.bad.melody.services.ComentarioCancionService;

@Service
public class ComentarioCancionServiceImpl implements ComentarioCancionService {

    @Autowired
    ComentarioCancionRepository comentarioCancionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CancionRepository cancionRepository;

    @Override
    public void calificacionCancion(Cancion cancionId, Usuario usuarioId, int calificacion) {
        //Se esta dando una calificacion entre 1 a 5
        if (calificacion < 0 && calificacion > 6) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
        //Se esta creando una instancia de la clase comentarioCancion
        //donde se utiliza setCancion para calificar la cancion,
        //tambien se utiliza otro setUsuario para saber que persona califica
        ComentarioCancion comentario = new ComentarioCancion();
        comentario.setCancion(cancionId);
        comentario.setUsuario(usuarioId);
        comentario.setCalificacion(calificacion); // Asignar la calificación al atributo calificacion
        
        // Guardar el comentario en la base de datos
        comentarioCancionRepository.save(comentario);
    }

    //Es un metodo que utiliza un list para almacenar
    //Tiene como parametro recibir un id para poder realizar el promedio de una cancion especifica
    @Override
    public int obtenerPromedioCalificacion(Long cancionId) {

        List<ComentarioCancion> promedio = comentarioCancionRepository.findByCancion(cancionId);
    
    //Se verifica si la lista tiene elementos con metodo propio de List, isEmty()
    //De esta forma se verica si esta vacia
    if (promedio.isEmpty()) {
        return 0;
    }

    //Se declara una variable local para almacenar la cantidad de calificaciones
    int sumaCalificaciones = 1;
    //
    for (ComentarioCancion promedios : promedio) {
        sumaCalificaciones += promedios.getCalificacion();
    }

    return sumaCalificaciones / promedio.size();
    }
    /*
     */
}
