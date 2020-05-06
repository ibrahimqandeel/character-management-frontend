package com.rakuten.challenge.serviceImpl;

import com.rakuten.challenge.client.RaceAPIClient;
import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import com.rakuten.challenge.service.RaceService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RaceServiceImpl implements RaceService {
    private RaceAPIClient raceAPIClient;

    public RaceServiceImpl(RaceAPIClient raceAPIClient) {
        this.raceAPIClient = raceAPIClient;
    }

    @Override
    public Optional<AllRacesDto> getRaces() throws ResourceNotFoundException {
        try {
            return raceAPIClient.getRaces();
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
