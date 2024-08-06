package com.Ecommerce.Shop.entities;


import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String imageURL;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
}