package net.thumbtack.buscompany.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {
    PASS("PASS"),
    CARGO("CARGO");
    private final String value;
}
