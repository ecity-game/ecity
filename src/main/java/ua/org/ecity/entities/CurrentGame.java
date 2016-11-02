package ua.org.ecity.entities;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.security.Timestamp;

@Entity
@Table(name = "current_games")
public class CurrentGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Timestamp date;
    private String name;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
