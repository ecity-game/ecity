package ua.org.ecity.controllers;

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
import ua.org.ecity.entities.GameStatus;
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
    public AdminPanelResult deleteCity(@PathVariable("id") Integer id) {

        if (cityService.getCityByID(id) == null)
            return new AdminPanelResult(AdminPanelStatus.CITY_NOT_FOUND, id);

        cityService.deleteCity(id);
        return new AdminPanelResult(AdminPanelStatus.CITY_HAS_BEEN_DELETED, id);
    }


    @RequestMapping(value = "/admin/city/edit", method = RequestMethod.POST)
    @ResponseBody
    public AdminPanelResult editCity(@RequestParam int id, @RequestParam String name, @RequestParam int regionId,
                                     @RequestParam int longitude, @RequestParam int latitude, @RequestParam int population,
                                     @RequestParam Date establishment, @RequestParam String url) {

        if (cityService.getCityByID(id) == null)
            return new AdminPanelResult(AdminPanelStatus.CITY_NOT_FOUND, id);

        List<City> cities = cityService.getCitiesByName(name);
        if (cities.size() > 0) {
            City tempCity = cities.get(0);
            if (tempCity.getId() != id)
                return new AdminPanelResult(AdminPanelStatus.CITY_IS_IN_DATABASE, id);
        }

        City city = cityService.getCityByID(id);
        city.setName(name);
        city.setRegionId(regionId);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishment);
        city.setUrl(url);

        cityService.saveCity(city);

        return new AdminPanelResult(AdminPanelStatus.CITY_HAS_BEEN_CHANGED, id);
    }

    @RequestMapping(value = "/admin/city/add", method = RequestMethod.POST)
    @ResponseBody
    public AdminPanelResult addCity(@RequestParam String name, @RequestParam int regionId,
                                    @RequestParam int longitude, @RequestParam int latitude, @RequestParam int population,
                                    @RequestParam Date establishment, @RequestParam String url) {

        if (cityService.getCitiesByName(name).size() > 0)
            return new AdminPanelResult(AdminPanelStatus.CITY_IS_IN_DATABASE, cityService.getCity(name).getId());

        City city = new City();

        city.setName(name);
        city.setRegionId(regionId);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishment);
        city.setUrl(url);

        cityService.saveCity(city);

        return new AdminPanelResult(AdminPanelStatus.CITY_HAS_BEEN_ADDED, cityService.getCity(name).getId());
    }

    @RequestMapping("/admin/regions")
    public
    @ResponseBody
    List<Region> regions() {
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

        if (regionService.getRegion(name) != null)
            return new AdminPanelResult(AdminPanelStatus.REGION_IS_IN_DATABASE, regionService.getRegion(name).getId());

        Region region = new Region();
        region.setName(name);
        regionService.saveRegion(region);

        return new AdminPanelResult(AdminPanelStatus.REGION_HAS_BEEN_ADDED, regionService.getRegion(name).getId());
    }

    @RequestMapping(value = "/admin/region/edit")
    @ResponseBody
    public AdminPanelResult editRegion(@RequestParam int id, @RequestParam String name) {

        if (regionService.getRegionByID(id) == null)
            return new AdminPanelResult(AdminPanelStatus.REGION_NOT_FOUND, id);

        List<Region> cities = regionService.getRegionByName(name);
        if (cities.size() > 0) {
            Region tempRegion = cities.get(0);
            if (tempRegion.getId() != id)
                return new AdminPanelResult(AdminPanelStatus.REGION_IS_IN_DATABASE, id);
        }

        Region region = regionService.getRegionByID(id);

        region.setName(name);
        regionService.saveRegion(region);

        return new AdminPanelResult(AdminPanelStatus.REGION_HAS_BEEN_CHANGED, id);
    }
}
