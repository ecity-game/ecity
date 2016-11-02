package ua.org.ecity.entities;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.security.Timestamp;

@Entity
@Table(name = "games_statstics")
public class GameStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int move_number;
    private int game_id;
    private int city_id;
    private Timestamp move_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMove_number() {
        return move_number;
    }

    public void setMove_number(int move_number) {
        this.move_number = move_number;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public Timestamp getMove_time() {
        return move_time;
    }

    public void setMove_time(Timestamp move_time) {
        this.move_time = move_time;
    }
}
