/**
 * 
 */
package com.vn.tali.hotel.security;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatasourceConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.vn.tali.hotel.entity"); // Đường dẫn package chứa các entity class
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties());
        return em;
    }
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

	@Bean(name = "dataSource")
	public DataSource getDataSource() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.put("current_session_context_class", //
				env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		// Package contain entity classes
		factoryBean.setPackagesToScan("com.vn.tali.hotel.entity");
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		//
		SessionFactory sf = factoryBean.getObject();
		System.out.println("## getSessionFactory: " + sf);
		return sf;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

//	@Bean
//	public DataSource dataSource() {
//		HikariConfig hikariConfig = this.initHikariPoolingConfig("hikari-pool");
//		
//		HikariDataSource hikariPoolingDataSource = new HikariDataSource(hikariConfig);
//		
//		return hikariPoolingDataSource;
//	}
//
//	@Bean
//	@Primary
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource ds)
//			throws PropertyVetoException {
//		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//		entityManagerFactory.setDataSource(ds);
//		entityManagerFactory.setPackagesToScan(new String[] { "com.vn.tali.hotel" });
//		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
//		return entityManagerFactory;
//	}
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(new String[] { "com.vn.tali.hotel" });
//		sessionFactory.setHibernateProperties(hibernateProperties());
//		return sessionFactory;
//	}
//
//	private Properties hibernateProperties() {
//		Properties properties = new Properties();
//		properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//		properties.put(org.hibernate.cfg.Environment.SHOW_SQL, false);
//		properties.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
//		return properties;
//	}
//
//	@Bean
//	@Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory s) {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(s);
//		return txManager;
//	}
//	
//	private HikariConfig initHikariPoolingConfig(String poolName) {
//		HikariConfig hikariConfig = new HikariConfig();
//		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
//		
//		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/project?characterEncoding=UTF-8&useLegacyDatetimeCode=false&noAccessToProcedureBodies=true&serverTimezone=Asia/Bangkok");
//		hikariConfig.setUsername("root");
//		hikariConfig.setPassword("123456");
//		
//		hikariConfig.setPoolName(poolName);
//		return hikariConfig;
//	}
	
}