package raut.shubham.electroroute.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "vehicle")
public class EV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String model;
    private String brand;
}
