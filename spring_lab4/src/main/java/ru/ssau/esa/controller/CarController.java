package ru.ssau.esa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.Car;
import ru.ssau.esa.entity.EventType;
import ru.ssau.esa.notifications.JmsSenderService;
import ru.ssau.esa.repository.CarRepository;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;

import java.util.UUID;

@RestController
public class CarController {

    private final CarRepository repository;
    private final JmsSenderService jmsSenderService;

    @Autowired
    public CarController(CarRepository repository, JmsSenderService jmsSenderService) {
        this.repository = repository;
        this.jmsSenderService = jmsSenderService;
    }

    @GetMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Car> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/add/car", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String car_id, String brand,String color){
        Car car = new Car();
        car.setCar_id(UUID.randomUUID().toString());
        car.setBrand(brand);
        car.setColor(color);
        Car c = repository.save(car);
        jmsSenderService.sendEvent(Car.class, c, EventType.CREATE);
        return  new GoodResponse(c);
    }

    @GetMapping(path = "/delete/car", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private  ServerResponse delete(String car_id){
        Car car = repository.findById(car_id).orElse(null);
        if (car == null){
            return new BadResponse("No Car!");
        }
        if (!car.getPersons().isEmpty()){
            return new BadResponse("There is at least one car with an owner.");
        }
        repository.delete(car);
        jmsSenderService.sendEvent(Car.class, car, EventType.DELETE);
        return new GoodResponse();
    }
}
