package com.rakuten.challenge.client;


import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ResourceDuplicationException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@FeignClient(name = "${character.management.characters.api.name}", url = "${character.management.characters.api.uri}")
public interface CharacterAPIClient {
    @RequestMapping(method = RequestMethod.POST)
    CharacterDto createCharacter(@RequestBody CharacterDto characterDto) throws FeignException;

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    Optional<CharacterDto> viewCharacterInfo(@PathVariable String name) throws FeignException;
}
