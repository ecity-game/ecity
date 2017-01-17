//package ua.org.ecity.services;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import ua.org.ecity.entities.City;
//import ua.org.ecity.repository.RegionRepository;
//
//import java.io.IOException;
//import java.util.Date;
//
//public class Parser {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    RegionRepository regionRepository;
//
//    private static final String REGION = "Область";
//    private static final String ESTABLISHMENT = "Основан";
//    private static final String ESTABLISHMENT_2 = "Первое упоминание";
//    private static final String POPULATION = "Население";
//    private static final String GPS = "Координаты";
//
//    public City update(City city) throws IOException {
//        int regionId = -1;
//        int year = 0;
//        String populationS = "";
//        String gps = "";
//
//        Document doc = Jsoup.connect(city.getUrl()).get();
//
//        Elements vcard = doc.select("table.infobox.vcard");
//        Elements tr = vcard.select("tr");
//        for (Element el : tr) {
//
//            if (el.text().startsWith(REGION)) {
//                String[] strs = el.text().split(" ", 2);
//                String region = strs[1].split(" ")[0];
//                int index = region.indexOf(region.substring(0, 2), 1);
//                if (index > 0) {
//                    region = region.substring(0, index);
//                }
//                regionId = regionRepository.findByName(region).get(0).getId();
//            }
//
//            if (el.text().startsWith(ESTABLISHMENT)) {
//                String[] strs = el.text().split(" ", 2);
//                year = Integer.valueOf(strs[1]);
//            }
//
//            if (el.text().startsWith(ESTABLISHMENT_2)) {
//                String[] strs = el.text().split(" ");
//                if (year == 0) {
//                    year = Integer.valueOf(strs[2]);
//
//                }
//            }
//
//            if (el.text().startsWith(POPULATION)) {
//                String[] strs = el.text().split(" ", 2)[1].split("\\[")[0].split("\\D+");
//                for (String str : strs) {
//                    populationS += str;
//                }
//            }
//        }
//
//        if (regionId > 0 && regionId != city.getRegionId()) {
//            logger.info("In DB " + city.getName() + ": Region is " + city.getRegionId());
//            city.setRegionId(regionId);
//            logger.info("In Wiki " + city.getName() + ": Region is " + city.getRegionId());
//        } else {
//            logger.info(city + " Region is OK");
//        }
//
//        Date establishment = new Date();
//        establishment.setYear(year);
//        if (!establishment.equals(city.getEstablishment().getYear())) {
//            logger.info("In DB " + city.getName() + ": Establishment is " + city.getEstablishment().getYear());
//            city.setEstablishment(establishment);
//            logger.info("In Wiki " + city.getName() + ": Establishment is " + city.getEstablishment().getYear());
//        } else {
//            logger.info(city + " Establishment is OK");
//        }
//
//        int population = Integer.valueOf(populationS);
//        if (population != city.getPopulation()) {
//            logger.info("In DB " + city.getName() + ": Population is " + city.getPopulation());
//            city.setPopulation(population);
//            logger.info("In Wiki " + city.getName() + ": Population is " + city.getPopulation());
//        } else {
//            logger.info(city + " Population is OK");
//        }
//
//
//        return city;
//    }
//
//}
//
