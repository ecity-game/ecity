package ua.org.ecity.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.org.ecity.entities.City;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameStatisticServiceTest {

    @InjectMocks
    GameStatisticService gameStatisticService;

    @Mock
    CityService cityService;

    List<City> usedCities;
    List<City> cities;

    @Before
    public void setUp() {
        usedCities = new LinkedList<>();
        cities = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            City city = new City();
            city.setName("Name_" + i);
            usedCities.add(city);
            cities.add(city);
        }
    }

    @Test
    public void getServerMoveTest() {
        City serverCity = null;

        City currentCity = new City();
        currentCity.setName("Name_3");

        City city = new City();
        city.setName("Name_" + 10);

        cities.add(city);
        when(cityService.getCitiesByFirstLetter(currentCity.getLastChar())).thenReturn(cities);

        serverCity = gameStatisticService.getServerMove(currentCity, usedCities);
        assertThat(serverCity.toString(),
                is("City{" +
                        "id=0, " +
                        "name='Name_10', " +
                        "longitude=0, " +
                        "latitude=0, " +
                        "population=0, " +
                        "establishment=null, " +
                        "url='null'}"
                ));

        usedCities.add(city);
        serverCity = gameStatisticService.getServerMove(currentCity, usedCities);
        assertThat(serverCity, is(nullValue()));
    }
}