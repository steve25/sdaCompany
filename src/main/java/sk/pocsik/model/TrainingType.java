package sk.pocsik.model;

import lombok.Builder;

import java.util.List;

@Builder
public record TrainingType(String name, String description, int capacity,
                           List<Training> trainings, Manufacturer manufacturer) {
}
