package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Manufacturer {
    private String name;
    private String id;
    private String address;
    private Boolean requiresTraining;
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}
