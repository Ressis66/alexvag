package ru.batchproject.alexvag;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  MyCustomReader myCustomReader;

  @Autowired
  MyCustomProcessor myCustomProcessor;

  @Autowired
  SimpleMongoConfig simpleMongoConfig;

  @Bean
  public Job createJob() {
    return jobBuilderFactory.get("MyJob")
        .incrementer(new RunIdIncrementer())
        .flow(createStep()).end().build();
  }

  @Bean
  public ItemWriter<MongoUser> writer() {
    MongoItemWriter<MongoUser> writer = new MongoItemWriter<MongoUser>();
    try {
      writer.setTemplate(simpleMongoConfig.mongoTemplate());
    } catch (Exception e) {
      e.printStackTrace();
    }
    writer.setCollection("mongousers");
    return writer;
  }

  @Bean
  public Step createStep() {
    return stepBuilderFactory.get("MyStep")
        .<User, MongoUser> chunk(1)
        .reader(myCustomReader)
        .processor(myCustomProcessor)
        .writer(writer())
        .build();
  }




}
