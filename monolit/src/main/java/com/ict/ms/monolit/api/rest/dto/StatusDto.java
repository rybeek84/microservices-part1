package com.ict.ms.monolit.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ict.ms.monolit.domain.Status;
import com.ict.ms.monolit.domain.exception.InvalidStatusException;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public class StatusDto {
    String value;

    @JsonCreator
    StatusDto(){
    }

    public StatusDto(String value) {
        this.value = value;
    }

    public Status getAsEnum(){
        return Stream.of(Status.values())
                .filter(status -> status.name().equals(value))
                .findAny()
                .orElseThrow(() -> new InvalidStatusException(value));
    }
}
