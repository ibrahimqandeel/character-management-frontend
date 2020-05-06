package com.rakuten.challenge.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class ClassDto {

    private String index;
    private String name;
    private String url;

    public ClassDto() {
    }

    public ClassDto(String index, String name, String url) {
        this.index = index;
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDto classDto = (ClassDto) o;
        return Objects.equals(index, classDto.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
