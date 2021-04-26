package com.EjemploSpringBatch.App.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Persona")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Persona {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String Nombre;
    private String Apelido;

    public Persona(String nombre, String apellido){
        this.Nombre = nombre;
        this.Apelido = apellido;
    }
}
