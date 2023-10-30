package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

// create Repository to interact with WikimediaData table in database
public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
