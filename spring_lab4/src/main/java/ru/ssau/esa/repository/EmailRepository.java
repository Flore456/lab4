package ru.ssau.esa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Email;

public interface EmailRepository extends CrudRepository<Email, String> {
}
