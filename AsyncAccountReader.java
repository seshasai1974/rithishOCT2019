package dre.spring.batch.acccount.job;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import dre.spring.batch.acccount.dto.AccountDTO;
import dre.spring.batch.acccount.mapper.AccountNumberRowMapper;
import dre.spring.batch.acccount.model.Account;
import dre.spring.batch.acccount.processor.AccountNumberProcessor;



@Configuration
@Component
@ConfigurationProperties("spring")
public class AsyncAccountReader {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private AccountNumberProcessor accountNumberProcessor;

	private String threadPoolSize;
	private String fileName;

	//@Autowired
	public AsyncAccountReader(JobBuilderFactory jobBuilderFactory, 
			                  StepBuilderFactory stepBuilderFactory,
			                  AccountNumberProcessor accountNumberProcessor, 
			                  @Value("${spring.batch.pool.size}") String threadPoolSize,
			                  @Value("${spring.batch.accounts.file.name}") String fileName) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.accountNumberProcessor = accountNumberProcessor;
		this.threadPoolSize = threadPoolSize;
		this.fileName = fileName;
		System.out.println("========Thread Pool Size new changes: ========== " + threadPoolSize + "================== ");
		System.out.println("========Accounts number File name zzzzz: ========== " + fileName + "================== ");

	}

	@Qualifier(value = "demo4")
	@Bean
	public Job demo4Job() throws Exception {
		return this.jobBuilderFactory.get("demo4").start(step1Demo4()).build();
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Step step1Demo4() throws Exception {
		return this.stepBuilderFactory.get("step1").<AccountDTO, Account>chunk(getChunkSize())
				.reader(accountReader())
				.processor(accountNumberProcessor)
				.writer(new NoOpItemWriter())
				.taskExecutor(taskExecutor()).build();
	}



	@Bean
	@StepScope
	public FlatFileItemReader<AccountDTO> accountReader() throws Exception {

		
		 FlatFileItemReader<AccountDTO> reader = new FlatFileItemReader<>();
		 Resource resource = new ClassPathResource(fileName);
		 System.out.println(" resource Loaded : " +LocalDateTime.now()+ "------" + resource.getFilename()) ;
		 reader.setResource(resource); 
		 //reader.setResource(inputFileResource(null));
		 String[] tokens = new String[]{"accountId"}; // here add the more column names for example  new String[]{"accountId","emailID", ...etc}; but dont forget to add the new column in AccountDTO along with the getter & setter methods
		 reader.setLineMapper(new DefaultLineMapper<AccountDTO>() {
			 { 
			 setLineTokenizer(new  DelimitedLineTokenizer() {
				 {					 
				 	
				 setNames(tokens);  
				 setDelimiter("|"); // set delimeter as pipe |
				 }
			 });  
		 setFieldSetMapper(new  AccountNumberRowMapper()); }});
		 
		
		return reader;
	}

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
		simpleAsyncTaskExecutor.setConcurrencyLimit(Integer.parseInt(threadPoolSize));
		return simpleAsyncTaskExecutor;
	}

	private int getChunkSize() {
		int chunkSize = 0;

		/*
		 * long lines = 0; try {
		 * 
		 * Resource resource = new ClassPathResource(fileName); InputStream is =
		 * resource.getInputStream();
		 * 
		 * byte[] c = new byte[1024]; int count = 0; int readChars = 0; boolean
		 * endsWithoutNewLine = false; while ((readChars = is.read(c)) != -1) { for (int
		 * i = 0; i < readChars; ++i) { if (c[i] == '\n') ++count; } endsWithoutNewLine
		 * = (c[readChars - 1] != '\n'); } if (endsWithoutNewLine) { ++count; } lines =
		 * count; } catch (IOException e) { e.printStackTrace(); }
		 */
		int lines = 0;
		try  
		{  
			Resource resource = new ClassPathResource(fileName); 
			
			//InputStream is = resource.getInputStream();
		FileReader fr=new FileReader(resource.getFile());  
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
		String line;
		
		while((line=br.readLine())!=null)  
		{  
			sb.append(line); 
			lines ++;//increase the count 
			sb.append("\n");     //line feed   
		}  
		fr.close();    //closes the stream and release the resources  
		
			System.out.println("Contents of File: ");  
			System.out.println(sb.toString());   //returns a string that textually represents the object  
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}  
		
		System.out.println("NEW LINES IN THE FILE: " + lines);
		chunkSize = (int) (lines / Integer.parseInt(threadPoolSize));
		System.out.println("chunksize : " + chunkSize);

		return chunkSize;

	}

}
