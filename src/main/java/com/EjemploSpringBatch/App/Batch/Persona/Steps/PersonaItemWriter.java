package com.EjemploSpringBatch.App.Batch.Persona.Steps;

import com.EjemploSpringBatch.App.Model.Persona;
import com.EjemploSpringBatch.App.Repository.PersonaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonaItemWriter implements ItemWriter<Persona> {
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public void write(List<? extends Persona> persona) throws Exception {
        personaRepository.saveAll(persona);
    }
}
