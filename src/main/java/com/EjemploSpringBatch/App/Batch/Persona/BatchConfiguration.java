package com.EjemploSpringBatch.App.Batch.Persona;

import com.EjemploSpringBatch.App.Batch.JobCompletionNotificationListener;
import com.EjemploSpringBatch.App.Batch.Persona.Steps.PersonaItemProcessor;
import com.EjemploSpringBatch.App.Batch.Persona.Steps.PersonaItemWriter;
import com.EjemploSpringBatch.App.Model.Persona;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public FlatFileItemReader<Persona> reader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile) {
        return new FlatFileItemReaderBuilder<Persona>()
                .name("personaItemReader")
                .resource(new PathResource(pathToFile))
                .delimited()
                .names  (new String[]{"Nombre", "Apellido"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Persona>() {{
                    setTargetType(Persona.class);
                }})
                .build();


    }

    @Bean
    public PersonaItemProcessor processor() {
        return new PersonaItemProcessor();
    }

    @Bean
    public PersonaItemWriter writer() {
        return new PersonaItemWriter();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(
            ItemReader<Persona> importReader) {
        return stepBuilderFactory
                .get("step1")
                .<Persona, Persona>chunk(10)
                .reader(importReader)
                .processor(processor())
                .writer(writer())
                .build();
    }

}
