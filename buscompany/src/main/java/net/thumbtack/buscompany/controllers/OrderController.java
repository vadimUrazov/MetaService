package net.thumbtack.buscompany.controllers;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import net.thumbtack.buscompany.dto.request.CreateOrderRequest;
import net.thumbtack.buscompany.dto.response.CreateOrderResponse;
import net.thumbtack.buscompany.dto.response.GetOrderResponse;
import net.thumbtack.buscompany.exception.ServiceException;
import net.thumbtack.buscompany.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

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
    public CreateOrderResponse createOrder(@Valid @Argument CreateOrderRequest request)
            throws Exception {
       return service.createOrder(request);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
    public GetOrderResponse getOrders() throws ServiceException {

        return service.getOrders();
    }

    @MutationMapping
    @PreAuthorize("hasRole('CLIENT')")
    public int deleteOrder(@Valid @Argument long id) throws ServiceException {
        service.deleteOrder(id);
        return 0;
    }

}
