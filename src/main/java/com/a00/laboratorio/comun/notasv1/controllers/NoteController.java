package com.a00.laboratorio.comun.notasv1.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.a00.laboratorio.comun.notasv1.entities.Note;
import com.a00.laboratorio.comun.notasv1.entities.dto.NoteDTO;
import com.a00.laboratorio.comun.notasv1.services.INoteService;
import com.a00.laboratorio.comun.notasv1.services.NoteServiceImpl;

@RestController 
@RequestMapping("/notes/v1")
public class NoteController {
    private final INoteService service;

    public NoteController(NoteServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Note>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping ("/id")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        Optional<Note> noteRequest = service.findById(id);
        if (noteRequest.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noteRequest);
    }

    @PostMapping
    public  ResponseEntity<Note> create(@RequestBody NoteDTO note) {
        Note noteCreate = service.create(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteCreate);

    }

    @PutMapping("/{id}")
    public  ResponseEntity<Note> update(@RequestBody NoteDTO note, @PathVariable UUID id) {
        Note noteUpdated = service.update(note, id);
        return ResponseEntity.ok(noteUpdated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    
}
