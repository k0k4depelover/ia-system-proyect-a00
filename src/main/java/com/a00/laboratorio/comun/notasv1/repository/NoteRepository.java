package com.a00.laboratorio.comun.notasv1.repository;


import java.util.UUID;

import org.springframework.data.repository.CrudRepository;


import com.a00.laboratorio.comun.notasv1.entities.Note;


public interface NoteRepository extends CrudRepository<Note, UUID> {

}
