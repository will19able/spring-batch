package com.EjemploSpringBatch.App.Controller;

import com.EjemploSpringBatch.App.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("batch")
public class IndexController {

    @Autowired
    PersonaService personaService;

    @PostMapping(value = "/procesarArchivo")
    public String procesarArchivo(@RequestBody MultipartFile archivo) throws IOException {
        personaService.subirArchivo(archivo);
        return "ok";
    }
}
