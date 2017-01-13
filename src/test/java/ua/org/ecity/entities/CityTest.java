package ua.org.ecity.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class CityTest {

    City city;

    @Before
    public void setUp() {
        city = new City();
    }

    @Test
    public void lastCharTest() {
        city.setName("Черновцы");
        assertThat(city.getLastChar(), is('В'));

        city.setName("Ы");
        assertThat(city.getLastChar(), is('Ы'));

        city.setName("АбвЙЫЬЪЦ");
        assertThat(city.getLastChar(), is('В'));

        city.setName("Ирпень");
        assertThat(city.getLastChar(), is('Н'));

        city.setName("Одесса");
        assertThat(city.getLastChar(), is('А'));

        city.setName("");
        assertThat(city.getLastChar(), nullValue());
    }
}