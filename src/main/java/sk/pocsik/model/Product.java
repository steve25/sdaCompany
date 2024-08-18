package sk.pocsik.model;

import lombok.Builder;

@Builder
public record Product(String name, double price, Manufacturer manufacturer) {
}
