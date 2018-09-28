package com.github.dgarcia202.goattracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "release")
public class Release {

    @Id
    private UUID id;

    private String version;

    @OneToMany
    @JoinColumn(name = "release_id")
    private List<Feature> features = new ArrayList<>();
}
