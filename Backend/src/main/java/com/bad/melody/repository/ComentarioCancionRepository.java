package com.bad.melody.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bad.melody.model.ComentarioCancion;

@Repository
public interface ComentarioCancionRepository   extends JpaRepository <ComentarioCancion, Long>{

    //Se esta creando un metodo nuevo para realizar una consulta especifica en la base de datos
    //List se utiliza porque se espera que retorne una lista
    //ComentarioCancion de la tabla que se esta realizando la consulta
    //finByCancion es una convecion de como se construlle el metodo
    //El parametro que recibe es un id para la consulta en especifico
    List<ComentarioCancion> findByCancion(Long cancionId);
}
