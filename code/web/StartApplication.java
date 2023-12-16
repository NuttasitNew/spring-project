package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class Start{
    public static void main(String[] args){
        ApplicationContext context ;
        context = SpringApplication.run(Setup.class);
    }
}

@SpringBootApplication
class Setup{
    @Bean
    DriverManagerDataSource connect(){
        return new DriverManagerDataSource(source);
    }
    String source = "jdbc:mysql://127.0.0.1"+
            "/coffeShop?user=jeff&password=0619";
}
