package sk.pocsik.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record Training(String Location, LocalDate date, List<Attendee> attendees, TrainingType trainingType) {
}
