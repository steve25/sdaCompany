package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Person {
    String id;
    String name;
    String phoneNumber;
    String email;
    Company company;
}
