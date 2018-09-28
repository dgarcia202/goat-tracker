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

    private String code;

    private String name;

    private String description;

    private Integer estimation;

    private Integer pctCompleted;

    @ManyToOne
    @JoinColumn(name = "feature_status_id")
    private FeatureStatus status;
}
