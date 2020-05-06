package com.rakuten.challenge.serviceImpl;

import com.rakuten.challenge.client.ClassAPIClient;
import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.exception.InternalServerException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import com.rakuten.challenge.service.ClassService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassAPIClient classAPIClient;

    public ClassServiceImpl(ClassAPIClient classAPIClient) {
        this.classAPIClient = classAPIClient;
    }

    public Optional<AllClassesDto> getClasses() throws ResourceNotFoundException, InternalServerException {
        try {
            return classAPIClient.getClasses();
        } catch (FeignException feignException) {
            if (feignException.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value());
            } else {
                log.error("Error: {} Exception: {}. Response code: {} ",
                        "Feign Client Exception",
                        feignException.getClass().getSimpleName(),
                        feignException.status());
                throw new InternalServerException();
            }
        }
    }
}
