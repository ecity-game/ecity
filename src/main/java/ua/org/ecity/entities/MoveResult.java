package ua.org.ecity.entities;

public class MoveResult {
    private boolean success;
    private String error;
    private String generatedCity;
    private int positionX;
    private int positionY;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getGeneratedCity() {
        return generatedCity;
    }

    public void setGeneratedCity(String generatedCity) {
        this.generatedCity = generatedCity;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
