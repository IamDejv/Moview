package cz.uhk.fim.pro2.moview.model;

public class Actor {
    private String fullname;

    public Actor(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "fullname='" + fullname + '\'' +
                '}';
    }
}
