package com.rakuten.challenge.client;


import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "${character.management.classes.api.name}", url = "${character.management.classes.api.uri}")
public interface ClassAPIClient {

    @RequestMapping(method = RequestMethod.GET)
    Optional<AllClassesDto> getClasses() throws FeignException;
}
