package sk.pocsik.model;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record Company(String name, String id, String address, List<Person> persons) {
    public static class CompanyBuilder {
        CompanyBuilder() {
            persons = new ArrayList<>();
        }
    }
}
