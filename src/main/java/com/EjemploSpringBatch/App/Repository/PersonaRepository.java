package com.EjemploSpringBatch.App.Repository;

import com.EjemploSpringBatch.App.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
