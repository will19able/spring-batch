package com.EjemploSpringBatch.App.Controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    void procesarArchivo() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("file", "sample-data.csv", "text/plain", "some CSV data".getBytes());
       /* mockMvc.perform(MockMvcRequestBuilders.multipart("/batch/procesarArchivo")
                .file(firstFile))
                .andExpect(status().is(200));*/
    }
}