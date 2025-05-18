package raut.shubham.electroroute.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import raut.shubham.electroroute.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  SignInResponse {

    private User user;

}