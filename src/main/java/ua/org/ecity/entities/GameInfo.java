package ua.org.ecity.entities;

public class GameInfo {
    private Integer Id;
    private String errorMessage;
    private Integer errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "{Id=" + Id +
                ", errorMessage='" + errorMessage + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}

