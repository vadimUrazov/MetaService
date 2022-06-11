package net.thumbtack.metasearchservice.entity;

import lombok.Getter;

@Getter
public enum Transport {
    BUS("BUS"),
    TRAIN("TRAIN");
    private final String type;

    Transport(String train) {
        this.type = train;
    }
}
