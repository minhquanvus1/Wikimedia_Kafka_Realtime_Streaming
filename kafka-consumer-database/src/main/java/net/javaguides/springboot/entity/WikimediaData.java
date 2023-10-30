package net.javaguides.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// define a message to be sent from Producer, which is also the record in wikimedia_recentchange table in database
@Entity
@Table(name = "wikimedia_recentchange")
@Getter
@Setter
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String wikiEventData;
}
