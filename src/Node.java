import java.util.ArrayList;

public class Node {
	private int id;
	private String name;
	
	public Node(String name) {
		this.name = name;
		setId();
	}
	
	private void setId() {
		switch(this.name) {
		case "Chinatown Complex Food Centre": id = 0; break;
		case "Old Airport Road Food Centre": id = 1; break;
		case "Tiong Bahru Market Hawker Centre": id = 2; break;
		case "Chomp Chomp Food Centre": id = 3; break;
		case "East Coast Lagoon Food Village": id = 4; break;
		case "Maxwell Food Centre": id = 5; break;
		case "Amoy Street Food Centre": id = 6; break;
		case "Bukit Timah Food Centre": id = 7; break;
		case "Marina Bay Sands": id = 8; break;
		default: id = Integer.MIN_VALUE;break;
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Node getNextClosest(ArrayList<Node> unvisited) {
		Node next = null;
		int minVal = Integer.MAX_VALUE;
		for (Node n: unvisited) {
			if (Walk.walkTime[this.getId()][n.getId()]<minVal && (this.getId() != n.getId())) {
				next = n;
				minVal = Walk.walkTime[this.getId()][n.getId()];
			}
		}
		return next;
	}
	
}
