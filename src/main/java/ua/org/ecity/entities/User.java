package ua.org.ecity.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String username;
    private String login;
    private String password;
    private boolean enable;
    private String email;
    private String lastname;
    private String citylives;
    @Column(name = "counterwin")
    private Integer counterwin;
    @Column(name = "counterloss")
    private Integer counterloss;


    public User() {
    }

    public User(String username, String login, String password, boolean enable, String email, String lastname, String citylives) {
        this.username = username;
        this.login = login;
        this.password = password;
        this.enable = enable;
        this.email = email;
        this.lastname = lastname;
        this.citylives = citylives;
    }

    public User(String username, Integer counterwin, Integer counterloss) {
        this.username = username;
        this.counterwin = counterwin;
        this.counterloss = counterloss;
    }

    public int getCounterwin() {
        return counterwin;
    }

    public void setCounterwin(int counterwin) {
        this.counterwin = counterwin;
    }

    public int getCounterloss() {
        return counterloss;
    }

    public void setCounterloss(int counterloss) {
        this.counterloss = counterloss;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCitylives() {
        return citylives;
    }

    public void setCitylives(String citylives) {
        this.citylives = citylives;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", counterwin=" + counterwin +
                ", counterloss=" + counterloss +
                '}';
    }
}
