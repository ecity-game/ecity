package ua.org.ecity.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Region;
import ua.org.ecity.repository.CityRepository;
import ua.org.ecity.repository.RegionRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class CityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    RegionRepository regionRepository;

    private static final String REGION = "Область";
    private static final String ESTABLISHMENT = "Основан";
    private static final String ESTABLISHMENT_2 = "Первое упоминание";
    private static final String POPULATION = "Население";
    private static final String GPS = "Координаты";

    public List<City> getCitiesByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<City> getCitiesByUrl(String url) {
        return cityRepository.findByUrl(url);
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public List<City> getCity(String name) {
        return cityRepository.getByName(name);
    }

    public City getCityByID(int id) {
        return cityRepository.getById(id);
    }

    public List<City> getCitiesByRegionId(Integer redionId) {
        return cityRepository.findByRegionId(redionId);
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public City update(City city) {
        try {
            int regionId = 0;
            int year = 0;
            int population = 0;
            String populationS = "";
            String gps = "";

            Document doc = Jsoup.connect(city.getUrl()).get();

            Elements vcard = doc.select("table.infobox.vcard");
            Elements tr = vcard.select("tr");
            for (Element el : tr) {

                if (el.text().startsWith(REGION)) {
                    String[] strs = el.text().split(" ", 2);
                    String region = strs[1].split(" ")[0];
                    int index = region.indexOf(region.substring(0, 2), 1);
                    if (index > 0) {
                        region = region.substring(0, index);
                    }
                    List<Region> regions = regionRepository.findByName(region);
                    try {
                        Region regionFromDB = regions.get(0);
                        regionId = regionFromDB.getId();
                    } catch (Exception ignored) {
                    }
                }

                if (el.text().startsWith(ESTABLISHMENT)) {
                    String[] strs = el.text().split(" ", 2);
                    try {
                        year = Integer.parseInt(strs[1]);
                    } catch (Exception ignored) {
                    }
                }

                if (el.text().startsWith(ESTABLISHMENT_2)) {
                    String[] strs = el.text().split(" ");
                    if (year == 0) {
                        try {
                            year = Integer.parseInt(strs[2]);
                        } catch (Exception ignored) {
                        }
                    }
                }

                if (el.text().startsWith(POPULATION)) {
                    String[] strs = el.text()
                            .split(" ", 2)[1]
                            .split("\\[")[0]
                            .split("\\(")[0]
                            .split("\\D+");

                    for (String str : strs) {
                        populationS += str;
                    }
                }
            }

            logger.info(city.getName() + " {");

            logger.info("Region:");
            if (regionId > 0 && regionId != city.getRegionId()) {
                logger.info("old: " + city.getRegionId());
                city.setRegionId(regionId);
                logger.info("new: " + city.getRegionId());
            } else {
                logger.info("OK");
            }

            Date establishment = new Date();
            establishment.setYear(year);
            logger.info("Establishment:");
            if (!establishment.equals(city.getEstablishment().getYear())) {
                logger.info("old: " + city.getEstablishment().getYear());
                city.setEstablishment(establishment);
                logger.info("new:" + city.getEstablishment().getYear());
            } else {
                logger.info("OK");
            }

            logger.info("Population:");
            try {
                population = Integer.parseInt(populationS);
            } catch (Exception ignored) {
            }
            if (population != city.getPopulation()) {
                logger.info("old: " + city.getPopulation());
                city.setPopulation(population);
                logger.info("new: " + city.getPopulation());
            } else {
                logger.info("OK");
            }

            logger.info("} " + city.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return city;
    }

    public void deleteCity(Integer id) {
        cityRepository.delete(id);
    }

    public List<City> getCitiesByFirstLetter(Character firstLetter) {
        return cityRepository.getByFirstLetter(firstLetter);
    }

    public boolean checkCityNotInBase(int id) {
        return cityRepository.countValues(id) == 0;
    }
}
