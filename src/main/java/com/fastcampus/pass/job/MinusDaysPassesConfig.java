package com.fastcampus.pass.job;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinusDaysPassesConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MinusPassTasklet minusPassTasklet;

    public MinusDaysPassesConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, MinusPassTasklet minusPassTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.minusPassTasklet = minusPassTasklet;
    }

    @Bean
    public Job minusDaysPassesJob() {
        return this.jobBuilderFactory.get("minusPassesJob")
                .start(minusDaysPassesStep())
                .build();
    }


    @Bean
    public Step minusDaysPassesStep() {
        return this.stepBuilderFactory.get("minusPassesStep")
                .tasklet(minusPassTasklet)
                .build();
    }
}
