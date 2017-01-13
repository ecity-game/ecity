package ua.org.ecity.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.org.ecity.services.CityService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {

    @InjectMocks
    HelloController helloController;

    @Mock
    CityService cityService;

    @Test
    public void citiesTest() {
        helloController.cities();
        verify(cityService, times(1)).getCities();
    }

    @Test
    public void cityInfoTest() {
        int id = 1;
        when(cityService.checkCityNotInBase(id)).thenReturn(false);
        helloController.cityInfo(id);
        verify(cityService, times(1)).getCityByID(id);
    }

    @Test
    public void cityInfoCityNotFoundTest() {
        int id = 1;
        when(cityService.checkCityNotInBase(id)).thenReturn(true);
        Object cityInfo = helloController.cityInfo(id);
        assertThat(cityInfo.toString(), is("AdminPanelResult{adminPanelStatus=" +
                "{code=104, message='City not found'}, cityId=" + id + "}"));
    }
}
