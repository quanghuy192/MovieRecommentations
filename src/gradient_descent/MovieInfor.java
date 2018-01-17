package gradient_descent;

import java.util.List;

public class MovieInfor {

	private int isLike;
	private List<Friend> friendVoteList;

	public MovieInfor(int isLike, List<Friend> friendVoteList) {
		super();
		this.isLike = isLike;
		this.friendVoteList = friendVoteList;
	}

	public int isLike() {
		return isLike;
	}

	public List<Friend> getFriendVoteList() {
		return friendVoteList;
	}
}
