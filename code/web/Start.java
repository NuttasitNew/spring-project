package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
class Sample{
    @RequestMapping("/check")
    String method1(){
        return "It Work!!";
    }
}