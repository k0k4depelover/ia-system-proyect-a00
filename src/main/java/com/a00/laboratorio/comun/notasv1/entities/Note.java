package com.a00.laboratorio.comun.notasv1.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "notes") 
public class Note {
    @Id
    @GeneratedValue
    @Generated(event = EventType.INSERT)
    @Column(columnDefinition = "uuid", updatable = false, insertable = false)
    private UUID id;


    public Note() {
    }
    
    @Column (nullable = false, length = 200)
    private String title;

    @Column (nullable = false)
    private String body; 

    @Generated(event = EventType.INSERT)
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private OffsetDateTime createdAt;
    public Note(String title2, String body2) {
        //TODO Auto-generated constructor stub
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
