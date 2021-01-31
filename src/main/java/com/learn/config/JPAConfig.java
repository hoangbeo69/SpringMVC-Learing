package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


//ở đây đang config JPA sử dụng java config
//có thể thay thế bằng việc sử dụng XML config
@Configuration
@EnableJpaRepositories(basePackages = {"com.learn.repository"}) //config repository tại package newsrepository
@EnableTransactionManagement  //bật tính năng quản lý trasition cho JPA
public class JPAConfig {
    //Entity Manager Factory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource()); //Thực hiện các thông số cấu hình của database
        em.setPersistenceUnitName("persistence-data");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties()); //
        return em;
    }

    //Entity Transition
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    //Entity Transition
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Thực hiện thiết lập cấu hình trước khi open connection với database
     * @return
     */
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springmvcbasic");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    Properties additionalProperties(){
        Properties properties = new Properties();
        //Tạo data từ những entity của java class ở đây có nghĩa là lúc
        //ban đầu cần khởi tạo luôn table
        properties.setProperty("hibernate.hbm2ddl.auto","create-drop");
        //Thực hiện thêm sửa 1 ít thuộc tính không phải tạo mới cả 1 table
        //properties.setProperty("hibernate.hbm2ddl.auto","none");
        return properties;
    }
}
