package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Region;
import ua.org.ecity.services.CityService;
import ua.org.ecity.services.RegionService;

import java.util.Date;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    CityService cityService;
    @Autowired
    RegionService regionService;

    @RequestMapping("/admin/cities")
    public
    @ResponseBody
    List<City> cities() {
        return cityService.getCities();
    }

    @RequestMapping("/admin/city/delete/{id}")
    public String deleteCity(@PathVariable("id") Integer id) {

        cityService.deleteCity(id);
        return "successfully";
    }


    @RequestMapping(value = "/admin/city/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editCity(@RequestParam int id, @RequestParam String name, @RequestParam int regionId,
                           @RequestParam int longitude, @RequestParam int latitude, @RequestParam int population,
                           @RequestParam Date establishment, @RequestParam String url) {

        City city = cityService.getCityByID(id);

        city.setName(name);
        city.setRegionId(regionId);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishment);
        city.setUrl(url);

        cityService.saveCity(city);

        return name + " City has been added!!";
    }

    @RequestMapping(value = "/admin/city/add", method = RequestMethod.POST)
    @ResponseBody
    public String addCity(@RequestParam String name, @RequestParam int regionId,
                          @RequestParam int longitude, @RequestParam int latitude, @RequestParam int population,
                          @RequestParam Date establishment, @RequestParam String url) {

        City city = new City();

        city.setName(name);
        city.setRegionId(regionId);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishment);
        city.setUrl(url);

        cityService.saveCity(city);

        return name + " City has been changed!!";
    }

    @RequestMapping("/admin/regions")
    public
    @ResponseBody
    List<Region> regions() {
        return regionService.getRegions();
    }

    @RequestMapping("/admin/region/delete/{id}")
    public String deleteRegion(@PathVariable("id") Integer id) {

        regionService.deleteRegion(id);
        return "successfully";
    }

    @RequestMapping(value = "/admin/region/add")
    @ResponseBody
    public String addRegion(@RequestParam String name) {

        Region region = new Region();

        region.setName(name);
        regionService.saveRegion(region);

        return name + " Region has been added!!";
    }

    @RequestMapping(value = "/admin/region/edit")
    @ResponseBody
    public String editRegion(@RequestParam int id, @RequestParam String name) {

        Region region = regionService.getRegionByID(id);

        region.setName(name);
        regionService.saveRegion(region);

        return name + " Region has been changed!!";
    }

}
