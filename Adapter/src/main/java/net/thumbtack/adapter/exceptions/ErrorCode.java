package net.thumbtack.adapter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    DATABASE_ERROR("", "Error database"),
    SERVER_ERROR("", "Server error"),
    ENTITY_ERROR("", "Entity not found"),
    MISSING_PARAMETERS("", "Missing parameters"),
    ERROR_FIELD("", "Incorrect field in request"),
    JSON_ERROR("", "Cannot read json"),
    ERROR_VALIDATE("", "Error validate"),
    METHOD_NOT_ALLOWED("", "Request method not supported"),
    INCORRECT_URL("url", "Url not found"),
    LOGIN_ALREADY_USE("login", "Login already exists"),
    TRIP_NOT_FOUND("tripId", "Trip not found"),
    BUS_NOT_FOUND("busName", "There is no bus with this busName"),
    ORDER_IS_STRANGER("id", "Client not found"),
    USER_NOT_FOUND("token", "User not found"),
    ERROR_AUTHORIZED("login or password", "Incorrect login or password"),
    ORDER_NOT_FOUND("id", "Order not found"),
    PASSENGER_NOT_FOUND("passenger", "Passenger not found"),
    INCORRECT_PLACE("place", "This place is not free"),
    INCORRECT_TRIP(" dates", " dates cannot be not null."),
    NO_APPROVED_TRIP("tripId", "Order cannot be create, because he is not approved"),
    INCORRECT_ORDER("passengers", "There are no seats in this order"),
    INCORRECT_PASSENGERS("passengers", "Incorrect order,because list passengers is empty"),
    INCORRECT_DATE("date", "There is no flight on this date"),
    DATE_NOT_FOUND("date", "Incorrect date"),
    ERROR_ADMIN("id", "Admin cannot left system,because  he's the only one in the system"),
    INCORRECT_FIRSTNAME("firstName", "Incorrect firstname"),
    INCORRECT_LASTNAME("lastName", "Incorrect lastname"),
    INCORRECT_PASSPORT("passport", "Incorrect passport"),
    INCORRECT_PRICE("price", "Incorrect price"),
    INCORRECT_START_TIME("start", "Incorrect start time"),
    INCORRECT_DURATION("duration", "Incorrect duration time"),
    INCORRECT_PHONE("phone", "Phone has written no correctly"),
    USER_NOT_AUTHORIZED("token", "User not authorized"),
    USER_NOT_ADMIN("type User", "User not admin"),
    USER_NOT_CLIENT("type User", "User not client");
    @Setter
    private String field;
    private String message;

}
