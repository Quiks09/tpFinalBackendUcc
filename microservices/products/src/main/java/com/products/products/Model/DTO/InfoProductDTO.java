package com.products.products.Model.DTO;

import com.products.products.Model.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoProductDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Category category;
    private Integer stock;
}
