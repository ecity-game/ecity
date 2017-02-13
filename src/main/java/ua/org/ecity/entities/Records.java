package ua.org.ecity.entities;



public class Records {

    private String login;
    private Integer counterwin;
    private Integer counterloss;

    public Records(String login, Integer counterwin, Integer counterloss) {
        this.login = login;
        this.counterwin = counterwin;
        this.counterloss = counterloss;
    }

    public Records() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getCounterwin() {
        return counterwin;
    }

    public void setCounterwin(Integer counterwin) {
        this.counterwin = counterwin;
    }

    public Integer getCounterloss() {
        return counterloss;
    }

    public void setCounterloss(Integer counterloss) {
        this.counterloss = counterloss;
    }

    @Override
    public String toString() {
        return "Records{" +
                "login='" + login + '\'' +
                ", counterwin=" + counterwin +
                ", counterloss=" + counterloss +
                '}';
    }
}
