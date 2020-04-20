package ru.func.vkbot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.longpoll.LongPoll;
import com.petersamokhin.bots.sdk.objects.Message;

import java.util.Random;


public class Main {
    private static final String KEY = "3d073a95976f69f8a074679ece478de1a1f3aec5d75fdcd2a7c5e13f2d2be735fa50e592c01aa3b905c4f";
    private static final String MyName = "VKbot";
    private static String url = "https://tts.voicetech.yandex.net/generate?format=mp3&lang=ru&speaker=zahar&key=\" + yandexKey + \"&text=";;
    String yandexKey = "key";

    public static void main(String[] args) throws Exception {

        Group group = new Group(194437959, KEY);


        group.onCommand("!weather", message -> {
            Weather weather = new Weather();
            System.out.println("Testing 1 - Send Http GET request");
            String result = null;
            try {
                result = weather.res(weather.sendGet());
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
                            "!help\t Узнать доступные команды\n" +
                            "!weatherCity\t Установить текущий город(в разработке)\n" +
                            "!weather\t Узнать погоду в текущем городе(Москва по умолчанию)\n" +
                            "!loh\t Узнать лох Вы или нет\n"
                            );
            LongPoll poll = new LongPoll(group);

            help.send();
        });

        group.onCommand("!loh", message -> {
            Random rand = new Random();

            Message loh = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Ого! Да Вы, кажется, лох");
            loh.send();
        });



        group.onCommand("!weatherCity", message -> {

            Message weatherCity = new Message()
                    .from(group)
                    .to(message.authorId());
            weatherCity.send();
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
