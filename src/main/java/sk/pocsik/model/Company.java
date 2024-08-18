package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Company {
    private String name;
    private String id;
    private String address;

    @Builder.Default
    private List<Person> persons = new ArrayList<>();
}
