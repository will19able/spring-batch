package com.EjemploSpringBatch.App.Batch.Persona.Steps;

import com.EjemploSpringBatch.App.Model.Persona;
import org.springframework.batch.item.ItemProcessor;

public class PersonaItemProcessor implements ItemProcessor<Persona, Persona> {

    @Override
    public Persona process(Persona persona) throws Exception {
        final String nombre = persona.getNombre().toUpperCase();
        final String apellido = persona.getApelido().toUpperCase();

        final Persona personaTranformada = new Persona(nombre, apellido);
        return personaTranformada;
    }
}
