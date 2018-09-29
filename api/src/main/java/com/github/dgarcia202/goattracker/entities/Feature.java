package com.github.dgarcia202.goattracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "feature")
public class Feature {

    @Id
    private UUID id;

    private UUID projectId;

    @ManyToOne
    @JoinColumn(name = "release_id")
    private Release release;

    private String code;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "feature_status_id")
    private FeatureStatus status;
}
