package ru.ssau.esa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Event;

public interface EventRepository extends CrudRepository<Event, String> {
}
