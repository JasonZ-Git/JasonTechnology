package org.jason.birthday.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jason.birthday.model.Relative;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GetBirthdayRestController {

    private static Logger logger = LogManager.getLogger(GetBirthdayRestController.class);

    @GetMapping(value = "allRelatives")
    public List<String> getAll() {
        return Arrays.asList(Relative.values()).stream().map(item -> item.toString()).collect(Collectors.toList());
    }
}
