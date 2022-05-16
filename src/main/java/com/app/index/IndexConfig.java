package com.app.index;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Annotation to make this class a configuration class to contain Beans.
@Configuration
public class IndexConfig {
    
     //Annotation to make this method as a bean so that it runs.
    @Bean
    CommandLineRunner commandLineRunner(IndexRepository repository) {
        return args -> {

            Index SPX = new Index(
                "SPX",
                "S&P 500 INDEX",
                4463.21f,
                0.56f,
                24.96f
            );
            Index DJI = new Index(
                "DJI",
                "DOW JONES INDUSTRIAL AVERAGE INDEX",
                35186.45f,
                0.78f,
                273.88f
            );
            Index NDX = new Index(
                "NDX",
                "NASDAQ 100 INDEX",
                15095.84f,
                0.29f,
                43.42f
            );

            Index RUI = new Index(
                "RUI",
                "RUSSELL 1000 INDEX",
                2495.9199f,
                1.68f,
                41.3110f
            );

            Index NYA = new Index(
                "NYA",
                "NYSE COMPOSITE INDEX",
                16744.29f,
                1.40f,
                231.13f
            );

             //Hibernate is invoked to insert into DB.
            repository.saveAll(
                List.of(SPX, DJID, NDXD, RUI,NYA)
            );
        };
    }

}
