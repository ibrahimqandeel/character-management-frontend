package com.rakuten.challenge.client;


import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "${character.management.races.api.name}", url = "${character.management.races.api.uri}")
public interface RaceAPIClient {

    @RequestMapping(method = RequestMethod.GET)
    Optional<AllRacesDto> getRaces() throws FeignException;
}
