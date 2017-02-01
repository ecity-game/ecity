package ua.org.ecity.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int player1;
  private int player2;
  private boolean finished;
  private int moves;
  private int first_player;
  private int start;
  private int end;

  public Game() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPlayer1() {
    return player1;
  }

  public void setPlayer1(int player1) {
    this.player1 = player1;
  }

  public int getPlayer2() {
    return player2;
  }

  public void setPlayer2(int player2) {
    this.player2 = player2;
  }

  public boolean isFinished() {
    return finished;
  }

  public boolean getFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  public int getMoves() {
    return moves;
  }

  public void setMoves(int moves) {
    this.moves = moves;
  }

  public int getFirstPlayer() {
    return first_player;
  }

  public void setFirstPlayer(int first_player) {
    this.first_player = first_player;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }
}
