package com.bad.melody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bad.melody.services.impl.ComentarioCancionServiceImpl;

@RestController
@RequestMapping("/api/comentarioCanciones")
public class ComentarioCancionController {

    @Autowired
    ComentarioCancionServiceImpl comentarioCancionServiceImpl;
    
}
