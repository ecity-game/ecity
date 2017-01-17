package ua.org.ecity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.AdminPanelResult;
import ua.org.ecity.entities.AdminPanelStatus;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Region;
import ua.org.ecity.services.CityService;
import ua.org.ecity.services.RegionService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@RestController
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CityService cityService;
    @Autowired
    RegionService regionService;

    @RequestMapping("/admin/cities")
    @ResponseBody
    public List<City> cities() {

        return cityService.getCities();
    }

    @RequestMapping("/admin/city/delete/{id}")
    public AdminPanelResult deleteCity(@PathVariable("id") Integer id) {

        if (cityService.checkCityNotInBase(id))
            return new AdminPanelResult(AdminPanelStatus.CITY_NOT_FOUND, id);

        cityService.deleteCity(id);
        return new AdminPanelResult(AdminPanelStatus.CITY_HAS_BEEN_DELETED, id);
    }


    @RequestMapping(value = "/admin/city/edit", method = RequestMethod.POST)
    @ResponseBody
    public AdminPanelResult editCity(@RequestParam int id, @RequestParam String name, @RequestParam int regionId,
                                     @RequestParam int longitude, @RequestParam int latitude, @RequestParam int population,
                                     @RequestParam String establishment, @RequestParam String url) {

        if (cityService.checkCityNotInBase(id))
            return new AdminPanelResult(AdminPanelStatus.CITY_NOT_FOUND, id);

        List<City> cities = cityService.getCitiesByName(name);
        if (cities.size() > 0) {
            City tempCity = cities.get(0);
            if (tempCity.getId() != id)
                return new AdminPanelResult(AdminPanelStatus.CITY_IS_IN_DATABASE, id);
        }

        DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date establishmentForDataBase;
        try {
            establishmentForDataBase = format.parse(establishment);
        } catch (ParseException e) {
            return new AdminPanelResult(AdminPanelStatus.WRONG_ESTABLISHMENT_VALUE, id);
        }

        City city = cityService.getCityByID(id);
        city.setName(name);
        city.setRegionId(regionId);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishmentForDataBase);
        city.setUrl(url);

        cityService.saveCity(city);

        return new AdminPanelResult(AdminPanelStatus.CITY_HAS_BEEN_CHANGED, id);
    }

    @RequestMapping(value = "/admin/city/add", method = RequestMethod.POST)
    @ResponseBody
    public AdminPanelResult addCity(@RequestParam String name, @RequestParam int regionId,
                                    @RequestParam int longitude, @RequestParam int latitude, @RequestParam int population,
                                    @RequestParam String establishment, @RequestParam String url) {

        if (cityService.getCitiesByName(name).size() > 0) {
            int id = cityService.getCity(name).get(0).getId();
            return new AdminPanelResult(AdminPanelStatus.CITY_IS_IN_DATABASE, id);
        }

        DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        Date establishmentForDataBase;
        try {
            establishmentForDataBase = format.parse(establishment);
        } catch (ParseException e) {
            return new AdminPanelResult(AdminPanelStatus.WRONG_ESTABLISHMENT_VALUE, 0);
        }

        City city = new City();

        city.setName(name);
        city.setRegionId(regionId);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishmentForDataBase);
        city.setUrl(url);

        cityService.saveCity(city);
        int newId = cityService.getCity(name).get(0).getId();

        return new AdminPanelResult(AdminPanelStatus.CITY_HAS_BEEN_ADDED, newId);
    }

    @RequestMapping("/admin/regions")
    @ResponseBody
    public List<Region> regions() {
        return regionService.getRegions();
    }

    @RequestMapping("/admin/region/delete/{id}")
    public AdminPanelResult deleteRegion(@PathVariable("id") Integer id) {

        if (regionService.getRegionByID(id) == null)
            return new AdminPanelResult(AdminPanelStatus.REGION_NOT_FOUND, id);

        regionService.deleteRegion(id);
        return new AdminPanelResult(AdminPanelStatus.REGION_HAS_BEEN_DELETED, id);
    }

    @RequestMapping(value = "/admin/region/add")
    @ResponseBody
    public AdminPanelResult addRegion(@RequestParam String name) {

        if (regionService.checkIfRegionInDataBase(name)) {
            int regionId = regionService.getRegion(name).getId();
            return new AdminPanelResult(AdminPanelStatus.REGION_IS_IN_DATABASE, regionId);
        }
        Region region = new Region();
        region.setName(name);
        regionService.saveRegion(region);

        int newId = regionService.getRegion(name).getId();
        return new AdminPanelResult(AdminPanelStatus.REGION_HAS_BEEN_ADDED, newId);
    }

    @RequestMapping(value = "/admin/region/edit")
    @ResponseBody
    public AdminPanelResult editRegion(@RequestParam int id, @RequestParam String name) {

        if (regionService.getRegionByID(id) == null)
            return new AdminPanelResult(AdminPanelStatus.REGION_NOT_FOUND, id);

        List<Region> regions = regionService.getRegionByName(name);
        if (regions.size() > 0) {
            Region tempRegion = regions.get(0);
            if (tempRegion.getId() != id)
                return new AdminPanelResult(AdminPanelStatus.REGION_IS_IN_DATABASE, id);
        }

        Region region = regionService.getRegionByID(id);

        region.setName(name);
        regionService.saveRegion(region);

        return new AdminPanelResult(AdminPanelStatus.REGION_HAS_BEEN_CHANGED, id);
    }

    @RequestMapping("/admin/update")
    @ResponseBody
    public List<City> updateDB() {
        logger.info("GET: /admin/db_update");
        List<City> cities = cityService.getCities();
        List<City> citiesNew = new LinkedList<>();
        for (City city : cities) {
            citiesNew.add(cityService.update(city));
        }
        return citiesNew;
    }

    @RequestMapping("/admin/update/{id}")
    public City updateCity(@PathVariable("id") Integer id) {
        if (cityService.getCityByID(id) == null) return null;
        return cityService.update(cityService.getCityByID(id));
    }
}
