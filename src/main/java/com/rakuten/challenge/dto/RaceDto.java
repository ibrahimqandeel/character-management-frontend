package com.rakuten.challenge.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class RaceDto {

    private String index;
    private String name;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceDto raceDto = (RaceDto) o;
        return Objects.equals(index, raceDto.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
