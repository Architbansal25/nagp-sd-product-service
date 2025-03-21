package com.nagp.webcart.products.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specification {
    private String packOf;
    private String styleCode;
    private String fit;
    private String fabric;
    private String color;
}
