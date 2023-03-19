package userservice;

import javax.validation.Valid;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import userservice.dto.request.*;
import userservice.dto.response.*;

@Service
public class UserService {

  public RegisterUserDtoResponse registerUser(@Valid @Argument RegisterUserDtoRequest request){
    return null;
  }

  public LoginDtoResponse loginUser(@Valid @Argument LoginDtoRequest request){
    return null;
  }

  public CreateOrderResponse createOrder( @Valid @Argument CreateOrderRequest request){
    return null;
  }

  public GetFreePlacesDtoResponse getFreePlaces(@Valid @Argument GetFreePlacesDtoRequest request){
    return null;
  }

  public ChoosePlacesDtoResponse choosePlaces( @Valid @Argument ChoosePlacesDtoRequest request){
    return null;
  }

}
