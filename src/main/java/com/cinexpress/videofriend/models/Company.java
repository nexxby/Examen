package com.cinexpress.videofriend.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String email;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String Address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Movie> movies;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Customer> customers;
}
