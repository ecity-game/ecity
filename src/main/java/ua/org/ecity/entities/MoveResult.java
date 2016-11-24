package ua.org.ecity.entities;

public class MoveResult {
    private GameStatus gameStatus;
    private City city;

    public MoveResult(GameStatus gameStatus, City city) {
        this.gameStatus = gameStatus;
        this.city = city;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "MoveResult{" +
                "gameStatus=" + gameStatus +
                ", city=" + city +
                '}';
    }
}
