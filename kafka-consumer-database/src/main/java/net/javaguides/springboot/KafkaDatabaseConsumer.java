package net.javaguides.springboot;

import net.javaguides.springboot.entity.WikimediaData;
import net.javaguides.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    private WikimediaDataRepository wikimediaDataRepository;

    // dependency injection with wikimediaDataRepository
    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    // create a consume() function to listen to messages of a particular topic
    @KafkaListener(topics = "wikimedia_recentchange", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("message received --> %s", eventMessage));

        // create a WikimediaData object
        WikimediaData wikimediaData = new WikimediaData();
        // use Setter in Wikimedia class, to set the wikiEventData to be the received message
        wikimediaData.setWikiEventData(eventMessage);
        // use Repository to save each received message to database
        wikimediaDataRepository.save(wikimediaData);
    }

}
