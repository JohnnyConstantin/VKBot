package ru.func.vkbot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;


public class Main {
    private static final String KEY = "3d073a95976f69f8a074679ece478de1a1f3aec5d75fdcd2a7c5e13f2d2be735fa50e592c01aa3b905c4f";
    private static final String MyName = "VKbot";

    public static void main(String[] args) throws Exception {

        Group group = new Group(194437959, KEY);

        group.onCommand("!weather", message -> {
            String[] arg = message.getText().split(" ");
            String command = arg[1];
                        Weather weather = new Weather();
                         System.out.println("Testing 1 - Send Http GET request");
                         String result = "Города " + command + "не существует";
                             try {
                                     result = weather.res(weather.sendGet(command));
                                 } catch (Exception e) {
                                     e.printStackTrace();
                                 }
                             Message wether = new Message()
                             .from(group)
                             .to(message.authorId())
                             .text(result);
                             wether.send();
            });

        group.onCommand("!help", message -> {
            Message help = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Доступные команды:\n" +
                            "!help\t Узнать доступные команды\n"+
                            "!weather NAME_OF_CITY\t Узнать погоду в текущем городе\n" +
                            "!loh\t Узнать лох Вы или нет\n"
                            );
            help.send();
        });

        group.onCommand("!loh", message -> {
            Message loh = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Ого! Да Вы, кажется, лох");
            loh.send();
        });

        group.onStickerMessage( message -> {
            Message sticker = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Ого! Замечательный стикер! " + MyName + " Одобряет");
            sticker.send();
        });

        group.onVoiceMessage(message -> {
            Message voice = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("К сожалению, я пока не научился понимать человеческую речь");
            voice.send();
        });

        group.onMessage(message -> {
                Message toSend = new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Введенное Вами сообщение не является командой. " +
                                "Для списка доступных команд введите !help");
                toSend.send();
        });
    }
}
