package org.example.Config;

import org.example.Controller.CatatanKeuanganController;
import org.example.Controller.PenggunaCotroller;
import org.example.Repository.CatatanKeuanganRepository;
import org.example.Repository.PenggunaRepository;
import org.example.Service.CatatanKeuanganService;
import org.example.Service.PenggunaService;
import org.example.Util.RandomUUID;
import org.example.Util.stringToDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Component
public class BeanConfiguration {
    @Value("org.postgresql.Driver")
    private String dbDriver;
    @Value("jdbc:postgresql://localhost:5432/FinancialPlanner")
    private String url;
    @Value("postgres")
    private String dbUser;
    @Value("pony malta")
    private String dbPassword;

    @Bean
    DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setDriverClassName(dbDriver);
        return driverManagerDataSource;
    }

    @Bean
    @Scope("singleton")
    public CatatanKeuanganRepository getRepository(){
        return new CatatanKeuanganRepository(dataSource());
    }

    @Bean
    @Scope("singleton")
    public CatatanKeuanganService getService(){
        return new CatatanKeuanganService(getRepository());
    }

    @Bean
    @Scope("singleton")
    public CatatanKeuanganController getController(){return new CatatanKeuanganController(getService());}

    @Bean
    @Scope("singleton")
    public PenggunaRepository getRepositoryPengguna(){
        return new PenggunaRepository(dataSource());
    }

    @Bean
    @Scope("singleton")
    public PenggunaService getServicePengguna(){
        return new PenggunaService(getRepositoryPengguna());
    }

    @Bean
    @Scope("singleton")
    public PenggunaCotroller getControllerPengguna(){
        return new PenggunaCotroller(getServicePengguna());}

    @Bean
    public RandomUUID getRandomUUID(){
        return new RandomUUID();
    }

    @Bean
    public stringToDate strToDate(){ return new stringToDate();}
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}


