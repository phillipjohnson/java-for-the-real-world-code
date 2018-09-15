package com.letstalkdata.iscream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
            throws IOException{
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMappingLocations(loadResources());

        return sessionFactory;
    }

    @Autowired
    private ResourceLoader resourceLoader;

    private Resource[] loadResources() throws IOException {
        return ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                    .getResources("classpath*:*.hbm.xml");
    }

    // These are required in Spring Boot 2,
    // although we will not explicitly use them

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
