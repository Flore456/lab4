package ru.ssau.esa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.*;
import ru.ssau.esa.notifications.EmailSenderService;
import ru.ssau.esa.notifications.JmsSenderService;
import ru.ssau.esa.repository.PersonRepository;
import ru.ssau.esa.repository.BankRepository;
import ru.ssau.esa.repository.CarRepository;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.ServerResponse;
import java.sql.Date;
import java.util.UUID;

@RestController
public class PersonController {

    private final PersonRepository repository;
    private final CarRepository carRepository;
    private final BankRepository bankRepository;
    private final JmsSenderService jmsSenderService;


    @Autowired
    public PersonController(PersonRepository repository, CarRepository carRepository, BankRepository bankRepository, JmsSenderService jmsSenderService) {
        this.repository=repository;
        this.carRepository=carRepository;
        this.bankRepository=bankRepository;
        this.jmsSenderService = jmsSenderService;

    }

    @GetMapping(path = "/persons",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Person> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/add/person", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String id, String name, String first_name, String city, Date birthday,String bankId,String carId){
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName(name);
        person.setFirst_name(first_name);
        person.setCity(city);
        person.setBirthday(birthday);

        Bank b = bankRepository.findById(bankId).orElse(null);
        if (b == null){
            return new BadResponse("No Bank!");
        }
        person.setBank(b);

        Car c = carRepository.findById(carId).orElse(null);
        if (c == null){
            return new BadResponse("No Car!");
        }

        person.setCar(c);
        Person newPerson = repository.save(person);
        jmsSenderService.sendPersonUpdate(newPerson, EventType.CREATE);
        jmsSenderService.sendEvent(Person.class, newPerson, EventType.CREATE);
        return new GoodResponse(newPerson);

    }

    @GetMapping(path = "/delete/person",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

    private ServerResponse delete(String id){
        Person person = repository.findById(id).orElse(null);
        if (person == null){
            return new BadResponse("No Person!");
        }
        repository.delete(person);
        jmsSenderService.sendPersonUpdate(person, EventType.DELETE);
        jmsSenderService.sendEvent(Person.class, person, EventType.DELETE);
        return new GoodResponse();
    }


}
