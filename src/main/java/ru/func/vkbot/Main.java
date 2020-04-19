package ru.func.vkbot;

import com.petersamokhin.bots.sdk.objects.Message;
import com.petersamokhin.bots.sdk.clients.Group;

public class Main {
    public static void main(String[] args) {
        Group group = new Group(151083290, "access_token");

        group.onSimpleTextMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Что-то скучновато буковки читать. Картинку кинь лучше.")
                        .send()
        );
    }
}
