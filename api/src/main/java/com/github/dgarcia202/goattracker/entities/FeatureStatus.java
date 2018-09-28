package com.github.dgarcia202.goattracker.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "feature_status")
public class FeatureStatus {

    @Id
    private UUID id;

    private String description;
}
