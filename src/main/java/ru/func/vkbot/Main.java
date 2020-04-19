package ru.func.vkbot;

import com.petersamokhin.bots.sdk.longpoll.LongPoll;
import com.petersamokhin.bots.sdk.objects.Message;
import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.clients.Client;
import com.petersamokhin.bots.sdk.utils.vkapi.API;
import com.petersamokhin.bots.sdk.objects.Message

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private static final String KEY = "3d073a95976f69f8a074679ece478de1a1f3aec5d75fdcd2a7c5e13f2d2be735fa50e592c01aa3b905c4f";
    private String accessToken;
    private Integer id;
    private static API api;
    private LongPoll longPoll = null;
    public CopyOnWriteArrayList<Client.Command> commands = new CopyOnWriteArrayList<>();
    private ConcurrentHashMap<Integer, Chat> chats = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Group group = new Group(194437959, KEY);
        
        group.onTyping(userId -> {
            System.out.println("Пользователь https://vk.com/id" + userId + " начал печатать");
        });

        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Что-то скучновато буковки читать. Картинку кинь лучше.")
                        .send()
        );
    }
}
