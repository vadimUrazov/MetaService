package net.thumbtack.adapter.exceptions;

import graphql.GraphQLException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class AdapterExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public GraphQLException handler(ServiceException e) {
        return new GraphQLException(e);
    }

    @ExceptionHandler(Throwable.class)
    public GraphQLException handler(Throwable e) {
        return new GraphQLException(e);
    }

}
