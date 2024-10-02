package com.bad.melody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public ResponseEntity<String> checkDatabaseConnection() {
        try {
            // Intenta ejecutar una simple consulta SQL
            jdbcTemplate.execute("SELECT 1");
            return ResponseEntity.ok("Conexión a la base de datos exitosa.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}