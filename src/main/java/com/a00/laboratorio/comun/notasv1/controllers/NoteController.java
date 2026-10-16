package com.a00.laboratorio.comun.notasv1.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a00.laboratorio.comun.notasv1.entities.Note;
import com.a00.laboratorio.comun.notasv1.entities.dto.NoteRequest;
import com.a00.laboratorio.comun.notasv1.services.INoteService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final INoteService service;

    // Inyecta la interfaz, no la implementación concreta
    public NoteController(INoteService service) {
        this.service = service;
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        var note = service.findById(id);

        if (note.isEmpty()) {
            return ResponseEntity.status(404).body(ProblemDetail.forStatus(404));
        }

        return ResponseEntity.ok(note.get());
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody NoteRequest note) {
        Note created = service.create(note);
        URI location = URI.create("/api/v1/notes/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@Valid @RequestBody NoteRequest note, @PathVariable UUID id) {
        Note noteUpdated = service.update(note, id);
        return ResponseEntity.ok(noteUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> latest(@RequestParam(defaultValue = "20") @Min(1) @Max(100) int limit) {
        return ResponseEntity.ok(service.findAllWithLimit(limit));
    }
    
}