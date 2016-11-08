package ua.org.ecity.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.City;
import ua.org.ecity.services.DataBaseEditService;

import java.util.List;


@RestController
public class DataBaseEditController {

    @Autowired
    DataBaseEditService dataBaseEditService;

    @RequestMapping("/admin/cities")
    public List<City> test() {
        return (List<City>) dataBaseEditService.findAll();
    }
}
