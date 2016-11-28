package ua.org.ecity.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class GameInfo {
    private Integer id;
    //private Game game;
    private GameStatus gameStatus;

    public GameInfo(Integer id, GameStatus gameStatus) {
        this.id = id;
        this.gameStatus = gameStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }


}

