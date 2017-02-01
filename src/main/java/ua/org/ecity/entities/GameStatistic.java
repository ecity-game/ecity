package ua.org.ecity.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "games_statstics")
public class GameStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "move_number")
    private int moveNumber;

    @JoinColumn(name = "game_id")
    @ManyToOne
    private Game game;

    @JoinColumn(name = "city_id")
    @ManyToOne
    private City city;

    @Column(name = "move_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date moveTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(Date moveTime) {
        this.moveTime = moveTime;
    }
}
