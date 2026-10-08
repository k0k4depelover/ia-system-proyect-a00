package com.a00.laboratorio.comun.notasv1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.a00.laboratorio.comun.notasv1.entities.Note;
import com.a00.laboratorio.comun.notasv1.entities.dto.NoteDTO;

public interface INoteService {
    public Optional<Note> findById(UUID id);
    public List<Note> findAll();
    public Note create(NoteDTO noteRequest);
    public Note update(NoteDTO noteRequest, UUID id);
    public void delete(UUID id);
}
