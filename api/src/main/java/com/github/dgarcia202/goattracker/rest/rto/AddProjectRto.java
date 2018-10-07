package com.github.dgarcia202.goattracker.rest.rto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddProjectRto {

    @NotBlank(message = "Should provide a name for the project")
    private String name;

    @Size(max = 25, message = "Max length for description is 25 ;)")
    private String description;
}
