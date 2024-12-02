package org.example.translator.entities;

import jakarta.validation.constraints.Pattern;

public record TranslationDto(
    String source,

    @Pattern(regexp = "((?i)english|deutsch)", message = "Please input only english or deutsch.")
    String from,

    @Pattern(regexp = "((?i)english|deutsch)", message = "Please input only english or deutsch.")
    String to
) {}