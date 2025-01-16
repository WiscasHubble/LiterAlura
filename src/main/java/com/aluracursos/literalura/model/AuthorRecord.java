package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthorRecord(
        @JsonProperty("name") String name,
        @JsonProperty("birth_year") Integer birth_year,
        @JsonProperty("death_year") Integer death_year
) {
}
