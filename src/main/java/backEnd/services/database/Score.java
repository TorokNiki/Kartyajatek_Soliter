package backEnd.services.database;

public class Score {
    private String rank;
    private String name;
    private String point;

    public Score(String rank) {
        this.rank = rank;
    }

    public Score(String rank, String name, String point) {
        this.rank = rank;
        this.name = name;
        this.point = point;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
