package sk.pocsik.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Manufacturer(String name, String id, String address, Boolean requiresTraining,
                           List<Product> products) {
}
