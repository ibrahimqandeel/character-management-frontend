package com.rakuten.challenge.controller;

import com.rakuten.challenge.dto.*;
import com.rakuten.challenge.exception.BadRequestException;
import com.rakuten.challenge.exception.InternalServerException;
import com.rakuten.challenge.exception.ResourceDuplicationException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import com.rakuten.challenge.service.CharacterService;
import com.rakuten.challenge.service.ClassService;
import com.rakuten.challenge.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final RaceService raceService;
    private final ClassService classService;

    private List<RaceDto> allRaces;
    private List<ClassDto> allClasses;

    @ModelAttribute("allRaces")
    public List<RaceDto> allRaces() {
        return allRaces;
    }

    @ModelAttribute("allClasses")
    public List<ClassDto> allClasses() {
        return allClasses;
    }

    public CharacterController(CharacterService characterService, RaceService raceService, ClassService classService) {
        this.characterService = characterService;
        this.raceService = raceService;
        this.classService = classService;
    }

    @PostConstruct
    private void init() throws ResourceNotFoundException {
        loadAllRaces();
        loadAllClasses();
    }

    private void loadAllRaces() {
        try {
            Optional<AllRacesDto> allRacesDto = raceService.getRaces();
            allRacesDto.ifPresent(racesDto -> allRaces = racesDto.getResults());
        } catch (ResourceNotFoundException | InternalServerException ex) {
            log.error("Error: {} Exception: {}. Method name: {}. Response code: {} ",
                    "Races Not Found",
                    ex.getClass().getSimpleName(),
                    "loadAllRaces",
                    ex.getHttpStatus());
        }
    }

    private void loadAllClasses() {
        try {
            Optional<AllClassesDto> allClassesDto = classService.getClasses();
            allClassesDto.ifPresent(classesDto -> allClasses = classesDto.getResults());
        } catch (ResourceNotFoundException | InternalServerException ex) {
            log.error("Error: {} Exception: {}. Reference Number: {}. Response code: {} ",
                    "Classes Not Found",
                    ex.getClass().getSimpleName(),
                    "loadAllClasses",
                    ex.getHttpStatus());
        }
    }

    @GetMapping(path = "/create-form")
    public String showCreateForm(Model model) {
        model.addAttribute("newCharacterDto", new CharacterDto());
        return "create";
    }

    @PostMapping
    public ModelAndView createCharacter(@ModelAttribute("newCharacterDto") @Valid CharacterDto characterDto,
                                        BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create");
        if (result.hasErrors()) {
            return modelAndView;
        }

        try {
            characterService.createCharacter(characterDto);
            modelAndView.setViewName("redirect:/?success");
            return modelAndView;
        } catch (ResourceDuplicationException e) {
            result.rejectValue("name", String.valueOf(HttpStatus.CONFLICT.value()), "There is already a character with this name");
        } catch (BadRequestException e) {
            result.rejectValue("name", String.valueOf(HttpStatus.BAD_REQUEST.value()), "Please make sure you entered valid data");
        }
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getCharacterInfo(@ModelAttribute("characterNameDto") @Valid CharacterNameDto characterNameDto,
                                         BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        if (result.hasErrors()) {
            return modelAndView;
        }

        try {
            Optional<CharacterDto> characterDetailsDto = characterService.getCharacterInfo(characterNameDto.getCharacterName());
            modelAndView.addObject("characterDetailsDto", characterDetailsDto.get());
            modelAndView.setViewName("details");
        } catch (ResourceNotFoundException e) {
            result.rejectValue("characterName", null, "No character with this name");
            return modelAndView;
        }
        return modelAndView;
    }
}
