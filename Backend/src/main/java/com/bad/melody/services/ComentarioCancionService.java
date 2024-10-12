package com.bad.melody.services;

import java.util.List;

import com.bad.melody.model.Cancion;
import com.bad.melody.model.ComentarioCancion;
import com.bad.melody.model.Usuario;

public interface ComentarioCancionService {

    void calificacionCancion (Cancion cancionId, Usuario usuarioId, int calificacion);

    int obtenerPromedioCalificacion (Long cancionId);

    ComentarioCancion dejarComentario(Long cancionId, Long usuarioId, String comentario);

    List<ComentarioCancion> obtenerComentarioPorCancion (Long idCancion);
}
