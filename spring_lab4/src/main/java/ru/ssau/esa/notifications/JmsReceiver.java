package ru.ssau.esa.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.ssau.esa.entity.Email;
import ru.ssau.esa.entity.Event;
import ru.ssau.esa.repository.EmailRepository;
import ru.ssau.esa.repository.EventRepository;


@Component
public class JmsReceiver {

    private final EmailSenderService emailSenderService;
    private final EventRepository eventRepository;
    private final EmailRepository emailRepository;

    @Autowired
    public JmsReceiver(EmailSenderService emailSenderService, EventRepository eventRepository, EmailRepository emailRepository) {
        this.emailSenderService = emailSenderService;
        this.eventRepository = eventRepository;
        this.emailRepository = emailRepository;
    }

    @JmsListener(destination = "eventbox", containerFactory = "myFactory")
    public void receiveEvent(Event event) {
        eventRepository.save(event);
    }

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        emailSenderService.send(email);
        emailRepository.save(email);
    }
}
