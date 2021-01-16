package ru.ssau.esa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Bank;

import java.util.Collection;
import java.util.List;

public interface BankRepository extends CrudRepository<Bank, String>{

    List<Bank> findByEmail(String email);
}
