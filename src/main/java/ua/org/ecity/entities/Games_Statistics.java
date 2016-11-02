package ua.org.ecity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.crypto.Data;

@Entity(name = "games_statistics")
public class Games_Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int move_number;
    private int game_id;
    private int city_id;
    private Data move_time;


}
