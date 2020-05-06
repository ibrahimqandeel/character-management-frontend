package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.exception.InternalServerException;
import com.rakuten.challenge.exception.ResourceNotFoundException;

import java.util.Optional;

public interface ClassService {
    Optional<AllClassesDto> getClasses() throws ResourceNotFoundException, InternalServerException;
}
