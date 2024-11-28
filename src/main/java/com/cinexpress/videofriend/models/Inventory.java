package com.cinexpress.videofriend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String itemType;
    @NonNull
    private Boolean availability;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie; // Relación con la película

    @NonNull
    @Column(nullable = false)
    private Integer quantity; // Cantidad de copias disponibles

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<Movie> movies;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<RelatedProduct> relatedProducts;


}
