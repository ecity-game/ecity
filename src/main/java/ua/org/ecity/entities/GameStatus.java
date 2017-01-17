package ua.org.ecity.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GameStatus {

    EXISTS(0, "Game exists"),
    DOESNT_EXIST(1, "Game doesn't exist"),
    GAME_FINISHED(2, "Game is finished"),
    NOCITY(10, "There isn't city in the base"),
    CITYUSE(11, "The City was use in game"),
    WRONGCITYLETTER(12, "The City start on wrong letter"),
    WINNERPLAYER1(20, " Winner Player 1"),
    WINNERPLAYER2(21, " Winner Player 2"),
    TIMES_IS_UP(22,"Time is up. Winner player 2"),
    USER_REGISTER_OK (31,"User registration OK "),
    USER_EXIST(32,"The player exist"),
    USER_PASSWORD_INCORECT(33,"User doesn't enter password"),
    USER_DOESNT_ENTER_LOGIN(34,"User doesn't enter login"),
    USER_ENTER_INCORRECT_EMAIL(35,"User enter incorrect e-mail"),
    USER_LOGIN_MUST_BE_LESS_20(36,"User Login must be less than 20 character");

    GameStatus(int code, String message) {
        this.code = code;
        this.message = message;

    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
