package raut.shubham.electroroute.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AuthRequestDTO {
    private String username;
    private String password;

}
