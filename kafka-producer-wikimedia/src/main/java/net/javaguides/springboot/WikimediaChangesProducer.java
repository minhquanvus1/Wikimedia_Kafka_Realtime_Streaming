package net.javaguides.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    // dependency injection with kafkaTemplate
    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // create sendMessage() function for Producer to start reading realtime data from URL, and send message/data to Topic
    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recentchange";

        // read realtime stream data from wikimedia, we use event source
        EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        // the Producer starts to read realtime data from URL (because the URL begins to send realtime data to Producer)
        eventSource.start();
        // This produce, and consume data will continuous in 10 miniutes, then this process will stop, wait for other action
        TimeUnit.MINUTES.sleep(10);
    }
}
