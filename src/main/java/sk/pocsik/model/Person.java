package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Person {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private Company company;
}
