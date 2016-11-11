package ua.org.ecity.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.City;
import ua.org.ecity.repository.DataBaseEditRepository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class DataBaseEditService{

    @Autowired
    private DataBaseEditRepository dataBaseEditRepository;


    public Iterable<City> findAll() {
        return dataBaseEditRepository.findAll();
    }

}
