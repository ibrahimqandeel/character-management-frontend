package com.rakuten.challenge.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CharacterNameDto {

    @NotEmpty
    private String characterName;
}
