package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.exception.BadRequestException;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ResourceDuplicationException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import feign.FeignException;

import java.util.Optional;

public interface CharacterService {
    CharacterDto createCharacter(CharacterDto characterDto) throws ResourceDuplicationException, BadRequestException;

    Optional<CharacterDto> getCharacterInfo(String name) throws ResourceNotFoundException;
}
