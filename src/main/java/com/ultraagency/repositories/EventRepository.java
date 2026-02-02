package com.ultraagency.repositories;

import com.ultraagency.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    //save(), findById(), findAll(), deleteById()
}
