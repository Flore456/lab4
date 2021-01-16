package ru.ssau.esa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.esa.entity.Car;

public interface CarRepository extends CrudRepository<Car,String>{

}
