package com.github.dgarcia202.goattracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    private UUID id;

    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<Release> releases = new ArrayList<>();
}
