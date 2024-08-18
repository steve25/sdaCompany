package sk.pocsik.model;

import lombok.Builder;

@Builder
public record Attendee(Boolean attended, Boolean succeeded, Training training, Person person) {
}
