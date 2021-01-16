package ru.ssau.esa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.ssau.esa.entity.Person;
import ru.ssau.esa.entity.Car;
import ru.ssau.esa.entity.Bank;
import ru.ssau.esa.repository.PersonRepository;
import ru.ssau.esa.repository.CarRepository;
import ru.ssau.esa.repository.BankRepository;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Controller
@RequestMapping("/xml")
public class XmlController {

    private final PersonRepository personRepository;
    private final CarRepository carRepository;
    private final BankRepository bankRepository;

    @Autowired
    public XmlController(PersonRepository personRepository, CarRepository carRepository, BankRepository bankRepository) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
        this.bankRepository = bankRepository;
    }

    @ResponseBody
    @GetMapping(path = "/banks", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getBanks() throws JsonProcessingException {
        Iterable<Bank> list =  bankRepository.findAll();
        return getModelAndView(list, "bankXSL");
    }

    @ResponseBody
    @GetMapping(path = "/persons", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getPersons() throws JsonProcessingException{
        Iterable<Person> list =  personRepository.findAll();
        return getModelAndView(list, "personXSL");
    }

    @ResponseBody
    @GetMapping(path = "/cars", produces = MediaType.APPLICATION_XML_VALUE)
    private ModelAndView getCars() throws JsonProcessingException{
        Iterable<Car> list =  carRepository.findAll();
        return getModelAndView(list, "carXSL");
    }

    private ModelAndView getModelAndView(Iterable<?> list, String viewName) throws JsonProcessingException {
        String str = new XmlMapper().writeValueAsString(list);
        ModelAndView mod = new ModelAndView(viewName);
        Source src = new StreamSource(new StringReader(str));
        mod.addObject("ArrayList", src);
        return mod;
    }
}
