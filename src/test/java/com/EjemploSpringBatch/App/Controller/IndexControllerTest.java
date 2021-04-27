package com.EjemploSpringBatch.App.Controller;

import com.EjemploSpringBatch.App.Service.PersonaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndexControllerTest {

    @Mock
    private PersonaService personaService;

    @InjectMocks
    private IndexController indexController;


    @Test
    void procesarArchivo() throws Exception {
        MockMultipartFile archive = new MockMultipartFile("file", "sample-data.csv", "text/plain", "some CSV data".getBytes());
        Mockito.when(personaService.subirArchivo(archive)).thenReturn("ok");

        String respuesta = indexController.procesarArchivo(archive);

        assertNotNull(respuesta);
        assertEquals(respuesta, "ok");

        /*MockMultipartFile firstFile = new MockMultipartFile("file", "sample-data.csv", "text/plain", "some CSV data".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/batch/procesarArchivo")
                .file(firstFile))
                .andExpect(status().is(200));*/
    }
}