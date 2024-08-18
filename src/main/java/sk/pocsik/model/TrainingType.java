package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class TrainingType {
    private String name;
    private String description;
    private int capacity;
    @Builder.Default
    private List<Training> trainings = new ArrayList<>();
    private Manufacturer manufacturer;
}
