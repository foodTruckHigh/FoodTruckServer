package com.high.food.common.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.serviceDb")
	public DataSource serviceDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "datasource.serviceDb_2")
	public DataSource serviceDataSource2(){
		return DataSourceBuilder.create().build();
	}
 	
	@Bean
	public DataSource routingDataSource(DataSource serviceDataSource, DataSource serviceDataSource2) {
		
		ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource(); 
		
		Map<Object, Object> datasourceMap = new HashMap<Object, Object>();
		
		datasourceMap.put("service", serviceDataSource);
		
		routingDataSource.setTargetDataSources(datasourceMap);
		routingDataSource.setDefaultTargetDataSource(serviceDataSource);
		
		return routingDataSource;
	}
	
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	   
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
	    hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
	    hibernateJpaVendorAdapter.setShowSql(true);
	    //hibernateJpaVendorAdapter.setGenerateDdl(true);
	 
	    HashMap<String, Object> jpaMap = new HashMap<String, Object>();
	    jpaMap.put("hibernate.ejb.naming_strategy",DEFAULT_NAMING_STRATEGY);
	    jpaMap.put("hibernate.format_sql", true);
	    //jpaMap.put("hibernate.hbm2ddl.auto", "validate");
	    jpaMap.put("hibernate.cache.use_second_level_cache", false);
	    jpaMap.put("hibernate.cache.use_query_cache", false);
	    jpaMap.put("hibernate.use_sql_comments", false);
	    jpaMap.put("hibernate.connection.autocommit", false);
	 
	    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    //localContainerEntityManagerFactoryBean.setMappingResources("META-INF/orm.xml");
	    localContainerEntityManagerFactoryBean.setDataSource(routingDataSource(serviceDataSource(),serviceDataSource2()));
	    localContainerEntityManagerFactoryBean.setPackagesToScan("com.high.fund.model.entity");
	    localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
	    localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaMap);
	    return localContainerEntityManagerFactoryBean;
	}
	  
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
	  return new JpaTransactionManager(entityManagerFactory().getObject());
	}
	 
	@Configuration
	@EnableJpaRepositories(
	     basePackages="com.high.fund.repository",
	     entityManagerFactoryRef = "entityManagerFactory",
	     transactionManagerRef = "transactionManager")
	static class DbArticleJpaRepositoriesConfig {
	}
}
