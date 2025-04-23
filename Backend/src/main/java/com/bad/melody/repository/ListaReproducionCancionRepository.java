package com.bad.melody.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bad.melody.model.ListaReproduccionCancion;

@Repository
public interface ListaReproducionCancionRepository extends JpaRepository<ListaReproduccionCancion, Long> {

    Optional<ListaReproduccionCancion> findByLista_IdAndCancion_Id(Long idLista, Long idCancion);

}
