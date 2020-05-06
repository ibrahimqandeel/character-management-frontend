package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.exception.InternalServerException;
import com.rakuten.challenge.exception.ResourceNotFoundException;

import java.util.Optional;

public interface RaceService {
    Optional<AllRacesDto> getRaces() throws ResourceNotFoundException, InternalServerException;
}
