package com.EjemploSpringBatch.App.Service;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@Service
@Slf4j
public class PersonaService {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importUserJob;

    public void subirArchivo(MultipartFile archivo) throws IOException {
        try {
            String path = "C:\\Users\\William Polo\\Documents\\JavaIntellijWordspace\\tmpuploads";
            File archivoAImportar = new File(archivo.getOriginalFilename());
            OutputStream outputStream = new FileOutputStream(path+archivoAImportar);
            IOUtils.copy(archivo.getInputStream(), outputStream);
            outputStream.flush();

            JobParametersBuilder builder = new JobParametersBuilder();
            builder.addDate("date", new Date());
            builder.addString("fullPathFileName", path+archivoAImportar.getName());
            JobExecution jobExecution = jobLauncher.run(importUserJob, builder.toJobParameters());

        } catch (IOException | JobInstanceAlreadyCompleteException | JobRestartException
                | JobParametersInvalidException | JobExecutionAlreadyRunningException e) {
            e.printStackTrace();

        }
    }
}
