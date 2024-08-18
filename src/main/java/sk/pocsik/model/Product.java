package sk.pocsik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Product {
    private String name;
    private double price;
    private Manufacturer manufacturer;
}
