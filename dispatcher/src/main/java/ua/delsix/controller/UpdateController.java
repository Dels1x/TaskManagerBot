package ua.delsix.controller;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.delsix.RabbitQueue;
import ua.delsix.service.UpdateProducer;

@Controller
public class UpdateController {
    private TelegramBot telegramBot;
    private final UpdateProducer updateProducer;

    public UpdateController(UpdateProducer updateProducer) {
        this.updateProducer = updateProducer;
    }

    public void registerBot(TelegramBot bot) {
        telegramBot = bot;
    }

    public void processUpdate(Update update) {
        if(update.getMessage().hasText()) {
            updateProducer.produce(RabbitQueue.MESSAGE_UPDATE, update);
        }
    }
}