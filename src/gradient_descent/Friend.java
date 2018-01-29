package gradient_descent;

public class Friend {
    public Friend(String name, double vote) {
        super();
        this.name = name;
        this.vote = vote;
    }

    private String name;
    private double vote;

    public String getName() {
        return name;
    }

    public double getVote() {
        return vote;
    }
}
