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

    private List<City> usedCities;
    private List<City> cities;

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
        City currentCity = new City();
        currentCity.setName("Name");

        City city = new City();
        city.setName("Name_" + 10);

        cities.add(city);
        when(cityService.getCitiesByFirstLetter(currentCity.getLastChar())).thenReturn(cities);
        assertThat(gameStatisticService.getNextMove(currentCity, usedCities), is(city));

        usedCities.add(city);
        assertThat(gameStatisticService.getNextMove(currentCity, usedCities), is(nullValue()));
    }
}