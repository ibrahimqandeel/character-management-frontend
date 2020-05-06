package com.rakuten.challenge.serviceImpl;

import com.rakuten.challenge.client.CharacterAPIClient;
import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.exception.ResourceDuplicationException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import com.rakuten.challenge.service.CharacterService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterAPIClient characterAPIClient;

    public CharacterServiceImpl(CharacterAPIClient characterAPIClient) {
        this.characterAPIClient = characterAPIClient;
    }

    @Override
    public CharacterDto createCharacter(CharacterDto characterDto) throws ResourceDuplicationException {
        try {
            return characterAPIClient.createCharacter(characterDto);
        } catch (FeignException feignException) {
            if (feignException.status() == HttpStatus.CONFLICT.value()) {
                throw new ResourceDuplicationException();
            } else {
                log.error("Error: {} Exception: {}. Response code: {} ",
                        "Feign Client Exception",
                        feignException.getClass().getSimpleName(),
                        feignException.status());
                throw feignException;
            }
        }
    }

    @Override
    public Optional<CharacterDto> getCharacterInfo(String name) throws ResourceNotFoundException {
        try {
            return characterAPIClient.viewCharacterInfo(name);
        } catch (FeignException feignException) {
            if (feignException.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException();
            } else {
                log.error("Error: {} Exception: {}. Response code: {} ",
                        "Feign Client Exception",
                        feignException.getClass().getSimpleName(),
                        feignException.status());
                throw feignException;
            }
        }
    }
}
