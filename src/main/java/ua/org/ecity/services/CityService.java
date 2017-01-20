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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RegionRepository regionRepository;

    private static final String REGION = "Область";
    private static final String ARC = "Автономная Республика Крым";
    private static final String ESTABLISHMENT = "Основан";
    private static final String ESTABLISHMENT_1 = "Дата основания";
    private static final String ESTABLISHMENT_2 = "Первое упоминание";
    private static final String ESTABLISHMENT_3 = "Город с";
    private static final String POPULATION = "Население";
    private static final String LOCATION = "Координаты";
    private static final String ARMS = "Герб";

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
            String year = "0000";
            int population = 0;
            double longitude = 0;
            double latitude = 0;
            String arms = "";

            Document doc = Jsoup.connect(city.getUrl()).get();

            Elements vcard = doc.select("table.infobox.vcard");
            Elements tr = vcard.select("tr");
            for (Element el : tr) {

                if (el.text().contains(ARC)) {
                    regionId = 5;
                }

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
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }

                if (el.text().startsWith(ESTABLISHMENT)) {
                    String[] strs = el.text().split(" ", 2);
                    year = strs[1];
                }

                if (year.equals("0000") && el.text().startsWith(ESTABLISHMENT_1)) {
                    String[] strs = el.text().split(" ");
                    year = strs[2];
                }

                if (year.equals("0000") && el.text().startsWith(ESTABLISHMENT_2)) {
                    String[] strs = el.text().split(" ");
                    year = strs[2];
                    try {
                        int test = Integer.parseInt(year);
                    } catch (Exception e) {
                        year = "0000";
                        logger.info(e.getMessage());
                    }
                }

                if (year.equals("0000") && el.text().startsWith(ESTABLISHMENT_3)) {
                    String[] strs = el.text().split(" ");
                    logger.info(Arrays.toString(strs));
                    year = strs[1];
                }

                if (el.text().startsWith(POPULATION)) {
                    try {
                        String[] strs = el.text()
                                .split(" ", 2)[1]
                                .split("\\[")[0]
                                .split("\\(");
                        if (strs[0].contains(",")) {
                            strs = strs[0].split(",");
                            population = (int) (1000 * Double
                                    .parseDouble(strs[0].split("\\D+") + "." + strs[1].split("\\D+")));
                        } else {
                            strs = strs[0].split("\\D+");
                            String popS = "";
                            for (String s : strs) {
                                popS += s;
                            }
                            population = Integer.parseInt(popS);
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }

                if (el.text().startsWith(LOCATION)) {
                    Elements geo = el.select("span").select("a");
                    try {
                        latitude = Double.parseDouble(geo.get(0).attr("data-lat"));
                        longitude = Double.parseDouble(geo.get(0).attr("data-lon"));
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }

            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element image : images) {
                if (image.attr("alt").equals(ARMS)) {
                    arms = "https:" + image.attr("src");
                    break;
                }
            }

            logger.info(city.getName() + " {");

            logger.info("\tRegion:");
            if (regionId > 0 && regionId != city.getRegionId()) {
                logger.info("\t\told: " + city.getRegionId());
                city.setRegionId(regionId);
                logger.info("\t\tnew: " + city.getRegionId());
            } else {
                logger.info("\t\tOK");
            }

            logger.info("\tEstablishment:");
            DateFormat format = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            Date establishment;
            try {
                if (year.split("\\D+").length > 1) {
                    year = year.split("\\D+")[1];
                }
                establishment = format.parse(year);
                Date establishmentDB = format.parse(city.getEstablishment().toString());
                if (!establishment.equals(establishmentDB)) {
                    logger.info("\t\told: " + establishmentDB);
                    city.setEstablishment(establishment);
                    logger.info("\t\tnew:" + city.getEstablishment().getYear());
                } else {
                    logger.info("\t\tOK");
                }
            } catch (Exception e) {
                logger.info("\tError: " + e);
            }

            logger.info("\tPopulation:");
            if (population != city.getPopulation()) {
                logger.info("\t\told: " + city.getPopulation());
                city.setPopulation(population);
                logger.info("\t\tnew: " + city.getPopulation());
            } else {
                logger.info("\t\tOK");
            }

            logger.info("\tLocation {");
            logger.info("\t\tLatitude:");
            if (latitude != city.getLatitude()) {
                logger.info("\t\t\told: " + city.getLatitude());
                city.setLatitude(latitude);
                logger.info("\t\t\tnew: " + city.getLatitude());
            } else {
                logger.info("\t\t\tOK");
            }
            logger.info("\t\tLongitude:");
            if (longitude != city.getLongitude()) {
                logger.info("\t\t\told: " + city.getLongitude());
                city.setLongitude(longitude);
                logger.info("\t\t\tnew: " + city.getLongitude());
            } else {
                logger.info("\t\t\tOK");
            }
            logger.info("\t}");

            logger.info("\tArms:");
            if (!arms.equals(city.getArms())) {
                logger.info("\t\told: " + city.getArms());
                city.setArms(arms);
                logger.info("\t\tnew: " + city.getArms());
            } else {
                logger.info("\t\tOK");
            }

            logger.info("} " + city.getName());

        } catch (IOException e) {
            logger.error(e.getMessage());
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
