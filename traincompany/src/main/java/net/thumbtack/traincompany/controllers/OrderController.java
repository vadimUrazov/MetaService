package net.thumbtack.traincompany.controllers;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.traincompany.dto.request.CreateOrderRequest;
import net.thumbtack.traincompany.dto.response.CreateOrderResponse;
import net.thumbtack.traincompany.dto.response.GetOrderResponse;
import net.thumbtack.traincompany.exception.ServiceException;
import net.thumbtack.traincompany.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Controller
@Validated
@Slf4j
public class OrderController {

    private final OrderService service;


    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @MutationMapping
    @PreAuthorize("hasRole('CLIENT')")
    public CreateOrderResponse createOrder(@Valid @Argument CreateOrderRequest request) throws ServiceException {
        return service.createOrder(request);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public GetOrderResponse getOrders() throws ServiceException {
        return service.getOrders();
    }

    @MutationMapping
    @PreAuthorize("hasRole('CLIENT')")
    public void deleteOrder(@Argument long id) throws ServiceException {
        service.deleteOrder(id);
    }

}
