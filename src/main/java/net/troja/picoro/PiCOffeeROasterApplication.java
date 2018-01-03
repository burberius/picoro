package net.troja.picoro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PiCOffeeROasterApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PiCOffeeROasterApplication.class, args);
    }
}
