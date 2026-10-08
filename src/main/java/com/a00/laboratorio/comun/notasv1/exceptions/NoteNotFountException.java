package com.a00.laboratorio.comun.notasv1.exceptions;

import java.util.UUID;

public class NoteNotFountException extends RuntimeException{
    
    private final UUID Id;
    public NoteNotFountException(UUID Id){
        super("Nota con ID: "+ Id  + " NO ENCONTRADA");

        this.Id= Id;
    }

    public UUID noteId(){
        return Id;
    }
}
