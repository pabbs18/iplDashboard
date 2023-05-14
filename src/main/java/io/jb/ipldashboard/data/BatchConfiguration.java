package io.jb.ipldashboard.data;


import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import io.jb.ipldashboard.model.Match;

@Configuration

public class BatchConfiguration {
	
	private static final String[] FILED_NAMES = {
			
			"ID","City","Date","Season","MatchNumber","Team1","Team2","Venue","TossWinner","TossDecision","SuperOver","WinningTeam","WonBy","Margin","method","Player_of_Match","Team1Players","Team2Players","Umpire1","Umpire2"
	};
	

	
	@Bean
	public FlatFileItemReader<MatchInput> reader() {
	  return new FlatFileItemReaderBuilder<MatchInput>()
	    .name("matchItemReader")
	    .resource(new ClassPathResource("match_data.csv"))
	    .delimited()
	    .names(FILED_NAMES)
	    .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {{
	      setTargetType(MatchInput.class);
	    }})
	    .build();
	}

	@Bean
	public MatchDataProcessor processor() {
	  return new MatchDataProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<Match>()
	    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO match (id,city,date,season,match_Number,team1,team2,venue,toss_Winner,toss_Decision,super_Over,winning_Team,won_By,result_Margin,method,player_Of_Match,team1Players,team2Players,umpire1,umpire2) "
	    		+ "VALUES (:id,:city,:date,:season,:matchNumber,:team1,:team2,:venue,:tossWinner,:tossDecision,:superOver,:winningTeam,:wonBy,:resultMargin,:method,:playerOfMatch,:team1Players,:team2Players, :umpire1,:umpire2)")
	    .dataSource(dataSource)
	    .build();
	}
	
	@Bean
	public Job importUserJob(JobRepository jobRepository,
	    JobCompletionNotificationListener listener, Step step1) {
	  return new JobBuilder("importUserJob", jobRepository)
	    .incrementer(new RunIdIncrementer())
	    .listener(listener)
	    .flow(step1)
	    .end()
	    .build();
	}

	@Bean
	public Step step1(JobRepository jobRepository,
	    PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Match> writer) {
	  return new StepBuilder("step1", jobRepository)
	    .<MatchInput, Match> chunk(10, transactionManager)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer)
	    .build();
	}
	
	

}
