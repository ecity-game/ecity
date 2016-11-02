package ua.org.ecity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "games")
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int player1;
    private int player2;
    private int finished;
    private int moves;
    private int first_player;
    private int start;
    private int end;

}
