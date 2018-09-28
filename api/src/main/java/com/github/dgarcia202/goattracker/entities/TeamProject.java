package com.github.dgarcia202.goattracker.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "release")
@IdClass(TeamProjectKey.class)
public class TeamProject implements Serializable {

    @Id
    @ManyToOne
    private Team team;

    @Id
    @ManyToOne
    private Project project;
}
