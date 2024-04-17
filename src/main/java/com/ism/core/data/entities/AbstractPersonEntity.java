package com.ism.core.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;

@MappedSuperclass
@Data
@AllArgsConstructor
public class AbstractPersonEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isArchived;

    private String nom;
    private String prenom;

    public AbstractPersonEntity(){
        this.isArchived=false;
    }
}
