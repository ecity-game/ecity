package ua.org.ecity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.crypto.Data;

@Entity(name = "current_games")
public class CurrentGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Data date;
    private String name;
    private String state;
    
}
