package net.thumbtack.adapter.service.delivery;

public interface Delivery<T> {
    String getTypeCompany();

    T send(String from, String to);

}
