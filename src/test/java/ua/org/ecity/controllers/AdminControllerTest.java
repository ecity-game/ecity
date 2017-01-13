package ua.org.ecity.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.org.ecity.entities.AdminPanelResult;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Region;
import ua.org.ecity.services.CityService;
import ua.org.ecity.services.RegionService;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    CityService cityService;

    @Mock
    RegionService regionService;

    @Mock
    City city;

    @Mock
    Region region;


    @Test
    public void citiesTest() {
        adminController.cities();
        verify(cityService, times(1)).getCities();
    }

    @Test
    public void deleteCityTest() {
        int id = 777;
        AdminPanelResult adminPanelResult = adminController.deleteCity(id);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=102, message='City has been deleted'}, cityId=" + id + "}"));
    }

    @Test
    public void deleteCityIdNotFoundTest() {
        int id = 777;
        when(cityService.checkCityNotInBase(id)).thenReturn(true);
        AdminPanelResult adminPanelResult = adminController.deleteCity(id);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=104, message='City not found'}, cityId=" + id + "}"));
    }

    @Test
    public void editCityTest() {
        int id = 7;
        when(cityService.getCityByID(id)).thenReturn(city);
        AdminPanelResult adminPanelResult = adminController.editCity(id, "Mirgorod", 1, 2, 1, 2, "2014", "http://");
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=101, message='City has been changed'}, cityId=" + id + "}"));

    }

    @Test
    public void editCityWrongCityNameTest() {
        int id = 7;
        String name = "Herson";
        when(cityService.checkCityNotInBase(id)).thenReturn(false);
        City tempCity = new City();
        tempCity.setId(8);
        when(cityService.getCitiesByName(name)).thenReturn(Arrays.asList(tempCity));
        AdminPanelResult adminPanelResult = adminController.editCity(id, name, 1, 2, 1, 2, "2014", "");
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=103, message=' City is already in database'}, cityId=" + id + "}"));
    }


    @Test
    public void editCityIdNotFoundTest() {
        int id = 7;
        when(cityService.checkCityNotInBase(id)).thenReturn(true);
        AdminPanelResult adminPanelResult = adminController.editCity(id, "", 1, 2, 1, 2, "2014", "");
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=104, message='City not found'}, cityId=" + id + "}"));
    }

    @Test
    public void editCityWrongEstablishmentTest() {
        int id = 7;
        AdminPanelResult adminPanelResult = adminController.editCity(id, "", 1, 2, 1, 2, "", "");
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=105, message='Wrong establishment data value'}, cityId=" + id + "}"));
    }


    @Test

    public void addCityIsInBaseTest() {
        String name = "Test";
        city.setName(name);
        when(cityService.getCitiesByName(name)).thenReturn(Arrays.asList(city));
        when(cityService.getCity(name)).thenReturn(Arrays.asList(city));
        AdminPanelResult adminPanelResult = adminController.addCity(name, 1, 2, 1, 2, "", "");
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=103, message=' City is already in database'}, cityId=" + city.getId() + "}"));

    }

    @Test
    public void addCityWrongEstablishmentDateTest() {
        AdminPanelResult adminPanelResult = adminController.addCity("", 1, 2, 1, 2, "", "");
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=105, message='Wrong establishment data value'}, cityId=0}"));
    }

    @Test
    public void addCityTest() {
        String name = "Test city";
        when(cityService.getCity(name)).thenReturn(Arrays.asList(city));
        AdminPanelResult adminPanelResult = adminController.addCity(name, 1, 2, 1, 2, "2014", "");
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=100, message='City has been added'}, cityId=" + city.getId() + "}"));
    }

    @Test
    public void regionsTest() {
        adminController.regions();
        verify(regionService, times(1)).getRegions();
    }

    @Test
    public void deleteRegionNotFoundTest() {
        int id = 777;
        AdminPanelResult adminPanelResult = adminController.deleteRegion(id);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=114, message='Region not found'}, cityId=" + id + "}"));
    }

    @Test
    public void deleteRegionTest() {
        int id = 1;
        when(regionService.getRegionByID(id)).thenReturn(new Region());
        AdminPanelResult adminPanelResult = adminController.deleteRegion(id);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=112, message='Region has been deleted'}, cityId=" + id + "}"));
    }

    @Test
    public void addRegionIsInBaseTest() {
        String name = "Test region";
        when(regionService.checkIfRegionInDataBase(name)).thenReturn(true);
        region.setId(1);
        region.setName(name);
        when(regionService.getRegion(name)).thenReturn(region);
        AdminPanelResult adminPanelResult = adminController.addRegion(name);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=113, message=' Region is already in database'}, cityId=" + region.getId() + "}"));
    }

    @Test
    public void addRegionTest() {
        String name = "Test region";
        when(regionService.checkIfRegionInDataBase(name)).thenReturn(false);
        when(regionService.getRegion(name)).thenReturn(region);
        AdminPanelResult adminPanelResult = adminController.addRegion(name);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=110, message='Region has been added'}, cityId=" + region.getId() + "}"));
    }

    @Test
    public void editRegionNotFoundTest() {
        int id = 1;
        String name = "Test";
        when(regionService.getRegionByID(id)).thenReturn(null);
        AdminPanelResult adminPanelResult = adminController.editRegion(id, name);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=114, message='Region not found'}, cityId=" + id + "}"));
    }

    @Test
    public void editRegionTest() {
        int id = 1;
        String name = "Test region";
        when(regionService.getRegionByID(id)).thenReturn(region);
        AdminPanelResult adminPanelResult = adminController.editRegion(id, name);
        assertThat(adminPanelResult.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=111, message='Region has been changed'}, cityId=" + id + "}"));
    }

}
