package ru.ssau.esa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ssau.esa.entity.EventType;
import ru.ssau.esa.entity.Bank;
import ru.ssau.esa.notifications.JmsSenderService;
import ru.ssau.esa.repository.BankRepository;
import ru.ssau.esa.response.BadResponse;
import ru.ssau.esa.response.GoodResponse;
import ru.ssau.esa.response.ServerResponse;
import java.util.UUID;

@RestController
public class BankController {
    private final BankRepository repository;
    private final JmsSenderService jmsSenderService;

    @Autowired
    public BankController(BankRepository repository, JmsSenderService jmsSenderService){

        this.repository = repository;
        this.jmsSenderService = jmsSenderService;
    }

    @GetMapping(path = "/banks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private Iterable<Bank> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/add/bank",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse add(String account_num, int account_year, int number_card, String manager_name, String email){
        if (!repository.findByEmail(email).isEmpty()){
            return new BadResponse("Duplicate email");
        }
        Bank bank = new Bank();
        bank.setAccount_num(UUID.randomUUID().toString());
        bank.setAccount_year(account_year);
        bank.setNumber_card(number_card);
        bank.setManager_name(manager_name);
        bank.setEmail(email);
        Bank b = repository.save(bank);
        jmsSenderService.sendEvent(Bank.class, b, EventType.CREATE);
        return new GoodResponse(b);
    }

    @GetMapping(path = "/delete/bank", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ServerResponse delete(String account_num){
        Bank bank = repository.findById(account_num).orElse(null);
        if (bank == null){
            return new BadResponse("No Bank!");
        }
        if (!bank.getPersons().isEmpty()){
            return new BadResponse("The Bank has customers!");
        }
        repository.delete(bank);
        jmsSenderService.sendEvent(Bank.class, bank, EventType.DELETE);
        return new GoodResponse();
    }
}
