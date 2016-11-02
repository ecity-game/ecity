package ua.org.ecity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "player_statistics")
public class PlayerStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long user_id;
    private long game_id;
    private boolean result;

}
