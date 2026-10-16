package com.a00.laboratorio.comun.notasv1.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.a00.laboratorio.comun.notasv1.entities.BackendSessionInfo;
import com.a00.laboratorio.comun.notasv1.repository.NoteRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController 
public class LabController {
    private final NoteRepository repository;
    
    public LabController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/lab/conexion")
    public ResponseEntity<BackendSessionInfo> conexion() {
        return ResponseEntity.ok(repository.getSessionInfo());
    }
    
}