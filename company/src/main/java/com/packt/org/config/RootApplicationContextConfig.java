package com.packt.org.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.packt.org")
public class RootApplicationContextConfig {

		
   @Bean
   public DataSource dataSource() {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      EmbeddedDatabase db = builder
    	 .setName("testdb")
         .setType(EmbeddedDatabaseType.HSQL)
         .addScript("db/sql/create-table.sql")
         .addScript("db/sql/insert-data.sql")
         .build();
      return db;
   }

   @Bean
   public NamedParameterJdbcTemplate getJdbcTemplate() {
      return new NamedParameterJdbcTemplate(dataSource());
   }
   
 //default username : sa, password : ''
	@PostConstruct
	public void getDbManager(){
	   DatabaseManagerSwing.main(
		new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
	}
 	
}
