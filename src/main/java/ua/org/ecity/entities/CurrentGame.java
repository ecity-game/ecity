package ua.org.ecity.entities;

import javax.persistence.*;
import javax.xml.crypto.Data;

@Entity
@Table(name = "current_games")
public class CurrentGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Data date;
    private String name;
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Data getDate() {
        return date;
    }

    public void setDate(Data date) {
        this.date = date;
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
}
