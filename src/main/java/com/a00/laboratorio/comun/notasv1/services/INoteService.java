package com.a00.laboratorio.comun.notasv1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.a00.laboratorio.comun.notasv1.entities.Note;
import com.a00.laboratorio.comun.notasv1.entities.dto.NoteRequest;

public interface INoteService {
    Optional<Note> findById(UUID id);
    List<Note> findAllWithLimit(int limit);
    List<Note> findAll();
    Note create(NoteRequest noteRequest);
    Note update(NoteRequest noteRequest, UUID id);
    void delete(UUID id);
}