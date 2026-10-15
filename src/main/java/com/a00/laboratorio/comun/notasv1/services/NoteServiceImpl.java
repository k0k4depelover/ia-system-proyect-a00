package com.a00.laboratorio.comun.notasv1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a00.laboratorio.comun.notasv1.entities.Note;
import com.a00.laboratorio.comun.notasv1.entities.dto.NoteRequest;
import com.a00.laboratorio.comun.notasv1.exceptions.InvalidNoteException;
import com.a00.laboratorio.comun.notasv1.exceptions.NoteNotFountException;
import com.a00.laboratorio.comun.notasv1.repository.NoteRepository;

@Service 
public class NoteServiceImpl implements INoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Note> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAll() {
        return (List<Note>) repository.findAll();
    }

    @Override
    @Transactional 
    public Note create(NoteRequest noteRequest) {
        try{
            String body = noteRequest.getBody() != null ? noteRequest.getBody() : "";
            Note note = new Note(noteRequest.getTitle(), body);
            return repository.save(note);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            throw new InvalidNoteException("El formato de la nota es invalido");
        }
    }

    @Override
    @Transactional 
    public Note update(NoteRequest noteRequest, UUID id) {
    try
        {        Note noteFind = repository.findById(id)
                .orElseThrow(() -> new NoteNotFountException(id));

        noteFind.setTitle(noteRequest.getTitle());
        noteFind.setBody(noteRequest.getBody() != null ? noteRequest.getBody() : "");
        return noteFind; // Hibernate sincroniza los cambios automáticamente por dirty checking
        }
        catch(RuntimeException e){
            throw new InvalidNoteException("El formato de la nota es invalido");
        }
    }

    @Override
    @Transactional 
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoteNotFountException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<Note> findAllWithLimit(int limit) {
        if (limit<= 0){
            throw new RuntimeException("Debes ingresar un limite de notas valido.");
        }
        return repository.findAllWithLimit(limit);
    }
}