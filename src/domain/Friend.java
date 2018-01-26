package domain;

public class Friend {
	public Friend(String name, int vote) {
		super();
		this.name = name;
		this.vote = vote;
	}

	private String name;
	private int vote;

	public String getName() {
		return name;
	}

	public int getVote() {
		return vote;
	}
}
