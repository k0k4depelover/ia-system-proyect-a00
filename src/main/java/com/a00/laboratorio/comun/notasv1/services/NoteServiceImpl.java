package com.a00.laboratorio.comun.notasv1.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a00.laboratorio.comun.notasv1.entities.Note;
import com.a00.laboratorio.comun.notasv1.entities.dto.NoteDTO;
import com.a00.laboratorio.comun.notasv1.exceptions.NoteNotFountException;
import com.a00.laboratorio.comun.notasv1.repository.NoteRepository;


@Service 
public class NoteServiceImpl implements INoteService {
    private final NoteRepository repository;
    public NoteServiceImpl(NoteRepository repository){
        this.repository= repository;
    }

    @Override
    @Transactional (readOnly= true)
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
    public Note create(NoteDTO noteRequest) {
        Note note= new Note(noteRequest.getTitle(), noteRequest.getBody());
        return repository.save(note);
    }

    @Transactional 
    @Override
    public Note update(NoteDTO noteRequest, UUID id) {
        Note noteFind = repository.findById(id).orElseThrow(() -> new NoteNotFountException(id) );

        noteFind.setTitle(noteRequest.getTitle());
        noteFind.setBody(noteRequest.getBody());
        return repository.save(noteFind);
        
    }

    @Transactional 
    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new NoteNotFountException(id);
        }
        repository.deleteById(id);
    }
    
}
