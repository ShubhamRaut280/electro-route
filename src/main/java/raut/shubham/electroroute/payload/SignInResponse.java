package raut.shubham.electroroute.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raut.shubham.electroroute.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  SignInResponse {

    private User user;

}