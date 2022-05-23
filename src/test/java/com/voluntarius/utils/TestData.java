package com.voluntarius.utils;

import com.voluntarius.models.Event;
import com.voluntarius.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestData {
    public static final Event TOKIO = new Event(1,
                                                "Tokio fight",
                                                "Large fight in tokyo with different creatures",
                                                LocalDateTime.parse("1999-11-01T10:11:11"),
                                                LocalDateTime.parse("1999-11-01T15:11:11"),
                                                "Tokyo, Japan",6);
    public static final Event CONCERT_NYAGAN = new Event(2,
                                                "Concert: my name is Hanzamay",
                                                "Ma name is Han ZAMAY all greatest tracks of han zamay",
                                                LocalDateTime.parse("2022-01-01T10:11:11"),
                                                LocalDateTime.parse("2024-11-01T15:11:11"),
                                                "Nyagan, Russia",5);
    public static final Event CONCERT_RYAZAN = new Event(3,
                                                "Concert: my name is Hanzamay",
                                                "Ma name is Han ZAMAY all greatest tracks of han zamay",
                                                LocalDateTime.parse("2022-01-01T10:11:11"),
                                                LocalDateTime.parse("2024-11-01T15:11:11"),
                                                "Ryazan, Russia",5);
    public static final Event CONCERT_TIVA = new Event(4,
                                                "Concert: my name is Hanzamay",
                                                "Ma name is Han ZAMAY all greatest tracks of han zamay",
                                                LocalDateTime.parse("2022-01-01T10:11:11"),
                                                LocalDateTime.parse("2024-11-01T15:11:11"),
                                                "Tiva, Russia",5);

    public static final User JKIM = new User(1,
            "Jhon",
            "Kim",
            "Jkim",
            "123",
            "jk@mail.ru",
            Collections.emptyList(),
            Collections.emptyList());
    public static final User KK = new User(2,
            "King",
            "Kong",
            "KK",
            "qwer",
            "KingKong@mail.ru",
            Collections.emptyList(),
            Collections.emptyList());
    public static final User AG = new User(3,
            "Anton",
            "Ton",
            "AG",
            "Ant",
            "Contex@mail.ru",
            Collections.emptyList(),
            Collections.emptyList());
    public static final User AND_HIS_NAME_IS = new User(4,
            "John",
            "Cena",
            "AndHisNameIs...",
            "jkl",
            "JohnCena@mail.ru",
            Collections.emptyList(),
            List.of(TOKIO));

    public static final User EMINEM = new User(5,
            "Marshal",
            "MM",
            "Eminem",
            "MyNameIs",
            "Eminem@mail.ru",
            List.of(CONCERT_NYAGAN, CONCERT_RYAZAN, CONCERT_TIVA),
            List.of(TOKIO, CONCERT_NYAGAN));

    public static final User LITTLE_REPTILE = new User(6,
            "Godzilla",
            "UAAARHHH",
            "LittleReptile",
            "WWWWW",
            "Godzilla@mail.ru",
            List.of(TOKIO),
            List.of(TOKIO));

    public static List<User> getTestUsers () {
        return new ArrayList<>(List.of(JKIM, KK, AG, AND_HIS_NAME_IS, EMINEM, LITTLE_REPTILE));
    }

}
