package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ClassService {
    Optional<AllClassesDto> getClasses() throws ResourceNotFoundException;
}
