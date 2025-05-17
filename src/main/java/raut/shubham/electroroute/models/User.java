package raut.shubham.electroroute.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "User")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String brand;

    private String model;
}