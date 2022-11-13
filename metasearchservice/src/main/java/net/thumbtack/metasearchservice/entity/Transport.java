package net.thumbtack.metasearchservice.entity;

import lombok.Getter;

@Getter
public enum Transport {
    BUS("BUS"),
    TRAIN("TRAIN"),
    SHIP("SHIP");
    private final String type;

    Transport(String type) {
        this.type = type;
    }
}
