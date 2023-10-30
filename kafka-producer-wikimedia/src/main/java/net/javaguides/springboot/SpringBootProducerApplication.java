package net.javaguides.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducerApplication.class);
    }

    @Autowired
    private WikimediaChangesProducer wikimediaChangesProducer;
    // the WikimediaChangesProducer will be triggered, and call sendMessage() method of WikimediaChangesProducer class when this main method runs
    @Override
    public void run(String... args) throws Exception {
        wikimediaChangesProducer.sendMessage();

    }
}