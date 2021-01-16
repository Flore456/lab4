package ru.ssau.esa.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.ssau.esa.entity.*;

@Service
public class JmsSenderService {

    private final JmsTemplate jmsTemplate;


    @Autowired
    public JmsSenderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void sendPersonUpdate(Person person, EventType eventType){
        Email email = new Email();
        Bank bank = person.getBank();
        email.setReceiver(bank.getEmail());
        email.setSubject("Person [" + eventType.name() + ']');
        String bodyPattern = "Morning, %s %s!\n\n" +
                "You have some changes!%s";
        String body = String.format(bodyPattern,
                bank.getAccount_year(), bank.getNumber_card(), bank.getManager_name(), eventType.name(), person.toString());
        email.setBody(body);
        jmsTemplate.convertAndSend("mailbox", email);
    }

    public <T> void sendEvent(Class<T> entityClass, T entity, EventType eventType){
        Event event = new Event();
        event.setEventType(eventType);
        event.setEntity(entity.toString());
        event.setEntityClass(entityClass.getSimpleName());
        jmsTemplate.convertAndSend("eventbox", event);
    }


}
