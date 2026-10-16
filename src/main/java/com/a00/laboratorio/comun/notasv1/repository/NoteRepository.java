package com.a00.laboratorio.comun.notasv1.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.a00.laboratorio.comun.notasv1.entities.BackendSessionInfo;
import com.a00.laboratorio.comun.notasv1.entities.Note;


public interface NoteRepository extends CrudRepository<Note, UUID> {

    @Query ("SELECT n FROM Note n order BY id DESC LIMIT ?1")
    public List<Note> findAllWithLimit(int limit);

    @Query (value = "SELECT pg_backend_pid() AS backend_pid, current_setting('statement_timeout') AS statement_timeout", nativeQuery=true)
    public BackendSessionInfo getSessionInfo();

}
