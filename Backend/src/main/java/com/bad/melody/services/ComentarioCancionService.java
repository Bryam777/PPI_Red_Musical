package com.bad.melody.services;

import com.bad.melody.model.Cancion;
import com.bad.melody.model.Usuario;

public interface ComentarioCancionService {

    void calificacionCancion (Cancion cancionId, Usuario usuarioId, int calificacion);

    int obtenerPromedioCalificacion (Long cancionId);
}
