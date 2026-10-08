package com.a00.laboratorio.comun.notasv1.entities.dto;

public class NoteDTO {
    private String title;
    private String body;
    
    public NoteDTO(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    
}
