package com.github.dgarcia202.goattracker.rest.rto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class AddFeatureRto {

    @NotNull
    @Size(max = 36, message = "Maximum length for this field is 36.")
    private UUID projectId;

    @NotNull
    @Size(max = 36, message = "Maximum length for this field is 36.")
    private UUID releaseId;

    @NotBlank
    private String name;

    private String description;
}
