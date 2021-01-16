package ru.ssau.esa.repository;

import ru.ssau.esa.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository  extends CrudRepository<Person, String>{

}
