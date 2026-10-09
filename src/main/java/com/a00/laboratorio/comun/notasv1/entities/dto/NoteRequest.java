package com.a00.laboratorio.comun.notasv1.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NoteRequest {
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 1, max = 150)
    private String title;

    @NotNull(message = "El cuerpo no puede ser nulo")

    private String body;

    public NoteRequest() {}

    public NoteRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}


