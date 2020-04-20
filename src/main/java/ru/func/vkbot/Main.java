package ru.func.vkbot;

import com.petersamokhin.bots.sdk.callbacks.Callback;
import com.petersamokhin.bots.sdk.callbacks.commands.OnCommandCallback;
import com.petersamokhin.bots.sdk.clients.User;
import com.petersamokhin.bots.sdk.longpoll.LongPoll;
import com.petersamokhin.bots.sdk.objects.Message;
import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.Client;
import com.petersamokhin.bots.sdk.utils.vkapi.API;
import org.json.JSONObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private static final String KEY = "3d073a95976f69f8a074679ece478de1a1f3aec5d75fdcd2a7c5e13f2d2be735fa50e592c01aa3b905c4f";
    private static final String MyName = "VKbot";
    public static void main(String[] args) {
        Group group = new Group(194437959, KEY);
        /*group.onTyping(userId -> {
            Message preAnswer = new Message()
                    .from(group)
                    .to(userId)
                    .text("Здравствуйте, https://vk.com/id" + userId + ", рад Вас видеть!");
            preAnswer.send();
        });*/

        group.onStickerMessage(message -> {
            Message sticker = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Поразительно красивый стикер! " + MyName + " одобряет");
            sticker.send();
        });

        group.onCommand("!help", message -> {
            Message help = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Доступные команды:\n" +
                            "!loh\n" +
                            "!music\n" +
                            "!weather\n" +
                            "!хуйзнает\n" +
                            "!хуйзнает\n" +
                            "!хуйзнает\n");

            help.send();
        });

        group.onCommand("!loh", message -> {
            Message loh = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Ого! Да Вы, кажется, лох");
            loh.send();
        });

        group.onCommand("!weather", message -> {
            Message weather = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Пока не готово, но будет погодка в регионе");
            weather.send();
        });

        group.onCommand("!хуйзнает", message -> {
            Message hz = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Да хуй его знает");
            hz.send();
        });

        group.onCommand("!music", message -> {
            Message hz = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Если здесь нет рандомного аудио, значит что-то пошло не так");
            hz.send();
        });

        group.onVoiceMessage(message -> {
            Message voice = new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Голосовухи для педиков, сударь. Пишите текстом, пожалуйста");
            voice.send();
        });

        group.onSimpleTextMessage(message -> {
                Message toSend = new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Введенное Вами сообщение не является командой. " +
                                "Для списка доступных команд введите !help");
                toSend.send();
        });
    }
}
