package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Attendee {
    private Boolean attended;
    private Boolean succeeded;
    private Training training;
    private Person person;
}
