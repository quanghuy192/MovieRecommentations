package gradient_descent;

import java.util.List;

public class MovieInfor {

    private double isLike;
    private List<Friend> friendVoteList;

    public MovieInfor(double isLike, List<Friend> friendVoteList) {
        super();
        this.isLike = isLike;
        this.friendVoteList = friendVoteList;
    }

    public double isLike() {
        return isLike;
    }

    public List<Friend> getFriendVoteList() {
        return friendVoteList;
    }
}
