package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Training {
    private String Location;
    private LocalDate date;
    @Builder.Default
    private List<Attendee> attendees = new ArrayList<>();
    private TrainingType trainingType;
}
