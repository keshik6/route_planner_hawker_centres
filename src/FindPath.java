import java.util.ArrayList;

public class FindPath {
	public static void main(String[] args) {
		ArrayList<Node> OriginallistOfPlaces  = new ArrayList<>();
		ArrayList<Node> listOfPlaces  = new ArrayList<>();
		
		//Make sure that the same node is not added more than once to the arraylist
		OriginallistOfPlaces.add(new Node("Bukit Timah Food Centre"));
		//OriginallistOfPlaces.add(new Node("Chinatown Complex Food Centre"));
		//OriginallistOfPlaces.add(new Node("Chomp Chomp Food Centre"));
		OriginallistOfPlaces.add(new Node("East Coast Lagoon Food Village"));
		//OriginallistOfPlaces.add(new Node("Old Airport Road Food Centre"));
		//OriginallistOfPlaces.add(new Node("Tiong Bahru Market Hawker Centre"));
		OriginallistOfPlaces.add(new Node("Amoy Street Food Centre"));
		
		//Make a deep copy of the original list for the other part of the code
		for (Node n: OriginallistOfPlaces) {
			listOfPlaces.add(n);
		}
		
		Node currentNode;
		Node nextNode;
		Path p = new Path();
		double budget = 20;
		
		currentNode = new Node("Marina Bay Sands");
		
		while (!listOfPlaces.isEmpty()) {
			//System.out.println("--------------Next iter---------------");
			nextNode = currentNode.getNextClosest(listOfPlaces);
			p.addPath(new Taxi(currentNode,nextNode));
			//System.out.println("Total cost is: " +  String.format("$%.2f",p.getTotalCost()));
			
			int i = 0;
			while (p.getTotalCost() > budget) {
				i++;
				p.modifySmallestSubpath(i);
				//System.out.println("Reduced cost to: $" + p.getTotalCost());
			}
			listOfPlaces.remove(nextNode);
			currentNode = nextNode;
		}
		
		nextNode = new Node("Marina Bay Sands");
		p.addPath(new Taxi(currentNode,nextNode));
		//System.out.println("Total cost is: " +  String.format("$%.2f",p.getTotalCost()));
		int i = 0;
		while (p.getTotalCost() > budget) {
			i++;
			p.modifySmallestSubpath(i);
			//System.out.println("Reduced cost to: $" + p.getTotalCost());
		}
		
		p.optimzePath(budget - p.getTotalCost());;
		
		//System.out.println("--------------End---------------");
		System.out.println(p.toString());
	}
	
	
}
