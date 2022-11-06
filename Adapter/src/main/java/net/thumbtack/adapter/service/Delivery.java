package net.thumbtack.adapter.service;

public interface Delivery<T> {
    T send(String from, String to);
}
