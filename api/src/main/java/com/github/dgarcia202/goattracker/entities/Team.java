package com.github.dgarcia202.goattracker.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "team")
public class Team {

    @Id
    private UUID id;

    private String name;
}
