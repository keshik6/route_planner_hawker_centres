import java.util.ArrayList;

interface Subpath{
    public double cost = 0;
    public int time = 0;
    public Node start = null;
    public Node end = null;
    public String toString();
    public double getCost();
    public int getTime();		//All the time is in minutes
    public int getSubpathDistance();
    public boolean isReachableByPT();
    public boolean isReachableByWalk();
    public Node getStartNode();
    public Node getEndNode();
    public boolean modified = false;
    public boolean isModified();
    public void setModified(boolean modified);
    
    
    //Time (Following the word document)
    //Taxi time
    public static final int[][] taxiTime = {{0,10,13,18,17,2,5,18,7},
    		{10,0,14,15,17,11,12,11,14},
    		{13,14,0,15,17,8,9,19,11},
    		{18,15,15,0,22,16,17,21,19},
    		{17,17,17,17,0,16,15,25,12},
    		{2,11,8,16,16,0,3,22,6},
    		{5,12,9,17,15,3,0,21,5},
    		{18,11,19,21,25,22,21,0,21},
    		{7,14,11,19,12,6,5,21,0}};
    
    public static final int[][] ptTime = {{0,29,16,46,52,Integer.MAX_VALUE,Integer.MAX_VALUE,37,16},
    		{29,0,27,40,49,27,31,31,28},
    		{16,27,0,40,55,24,21,41,27},
    		{46,40,40,0,62,49,49,55,50},
    		{52,49,55,55,0,16,15,25,12},
    		{Integer.MAX_VALUE,27,24,49,49,0,Integer.MAX_VALUE,38,17},
    		{Integer.MAX_VALUE,31,21,49,50,Integer.MAX_VALUE,0,37,16},
    		{37,31,41,55,58,38,37,0,21},
    		{16,28,27,50,45,17,16,31,0}};
    
    public static final int[][] walkTime = {{0,78,21,138,147,4,8,145,34},
    		{78,0,95,99,79,78,76,175,62},
    		{21,95,0,147,164,26,29,126,55},
    		{138,99,147,0,155,139,141,175,132},
    		{147,79,164,164,0,147,144,255,125},
    		{4,78,26,139,147,0,6,150,33},
    		{8,76,29,141,144,6,0,153,30},
    		{145,175,126,175,255,150,153,0,154},
    		{34,62,55,132,125,33,30,154,0}};
    
    
    public static final double[][] taxiCost = {{0,7.97,8.79,12.57,13.62,3.60,3.98,13.41,8.23},
    		{7.97,0,8.89,9.31,14.39,9.59,9.75,9.71,10.45},
    		{8.79,8.89,0,9.31,14.39,6.66,7.16,13.28,7.86},
    		{12.57,9.31,9.31,0,15.86,49,13.72,15.95,14.68},
    		{13.62,14.39,14.39,14.39,0,14.34,14.53,21.94,12.29},
    		{3.60,9.59,6.66,13.06,14.34,0,7.50,19.88,9.47},
    		{3.98,9.75,7.16,13.72,14.53,7.50,0,19.89,8.75},
    		{13.41,9.71,13.28,15.95,21.94,19.88,19.89,0,24.24},
    		{8.23,10.45,7.86,14.68,12.29,9.47,8.75,24.24,0}
    };
    
    
    public static final double[][] ptCost = {{0,0.97,0.77,1.37,1.45,Integer.MAX_VALUE,Integer.MAX_VALUE,1.53,0.77},
    		{0.97,0,1.07,1.23,1.49,1.07,1.16,1.33,1.07},
    		{0.77,1.07,0,1.23,1.57,0.77,0.77,1.41,0.87},
    		{1.37,1.23,1.23,0,1.49,1.41,1.41,1.61,1.41},
    		{1.45,1.49,1.57,1.57,0,1.49,1.49,1.75,1.37},
    		{Integer.MAX_VALUE,1.07,0.77,1.41,1.49,0,Integer.MAX_VALUE,1.53,0.77},
    		{Integer.MAX_VALUE,1.16,0.77,1.41,1.49,Integer.MAX_VALUE,0,1.53,0.77},
    		{1.53,1.33,1.41,1.61,1.75,1.53,1.53,0,1.45},
    		{0.77,1.07,0.87,1.41,1.37,0.77,0.77,1.45,0}
    };
    		
}
    

class Taxi implements Subpath{
    private double cost;
    private int time;
    private Node start;
    private Node end;
    private boolean modified;
    
    public Taxi(Node s, Node e){
        start = s;
        end = e;
        cost = taxiCost[s.getId()][e.getId()];
        time = taxiTime[s.getId()][e.getId()];
    }

    public double getCost(){
        return cost;
    }

    public int getTime(){
        return time;
    }
    
    public int getSubpathDistance() {
    	return Walk.walkTime[start.getId()][end.getId()];
    }
    
    
    public boolean isReachableByPT() {
		if (PT.ptCost[start.getId()][end.getId()] >= Integer.MAX_VALUE) {
			return false;
		}
		return true;
	}
    
    
    public boolean isReachableByWalk() {
    	if (Walk.walkTime[start.getId()][end.getId()]<=10) {
    		return true;
    	}
    	return false;
    }

    public Node getStartNode() {
		return this.start;
	}

	public Node getEndNode() {
		return this.end;
	}
    
    
    public String toString(){
        return "Take a taxi from " + start.getName() + " to " + end.getName();
    }
    
    public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
	

}

class PT implements Subpath{
    private double cost;
    private int time;
    private Node start;
    private Node end;
    private boolean modified;
    

    public PT(Node s, Node e){
        start = s;
        end = e;
        cost = ptCost[s.getId()][e.getId()];
        time = ptTime[s.getId()][e.getId()];
    }

     public double getCost(){
        return cost;
    }

    public int getTime(){
        return time;
    }
    
    public int getSubpathDistance() {
    	return Walk.walkTime[start.getId()][end.getId()];
    }
    
    public boolean isReachableByPT() {
		if (PT.ptCost[start.getId()][end.getId()] >= Integer.MAX_VALUE) {
			return false;
		}
		return true;	
	}
    
    public boolean isReachableByWalk() {
    	if (Walk.walkTime[start.getId()][end.getId()]<=10) {
    		return true;
    	}
    	return false;
    }
    
    public Node getStartNode() {
		return this.start;
	}

	public Node getEndNode() {
		return this.end;
	}
    
    
    public String toString(){
        return "Take public transport from " + start.getName() + " to " + end.getName();
    }
    
    public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}
}

class Walk implements Subpath{
    private double cost = 0;
    private int time;
    private Node start;
    private Node end;
    private boolean modified;
    
    public Walk(Node s, Node e){
        start = s;
        end = e;
        cost = 0;
        time = walkTime[s.getId()][e.getId()];
    }

    public double getCost(){
        return cost;
    }

    public int getTime(){
        return time;
    }
    
    public int getSubpathDistance() {
    	return Walk.walkTime[start.getId()][end.getId()];
    }
    
    
    public boolean isReachableByPT() {
		if (PT.ptCost[start.getId()][end.getId()] >= Integer.MAX_VALUE) {
			return false;
		}
		return true;
	}
    
    public boolean isReachableByWalk() {
    	if (Walk.walkTime[start.getId()][end.getId()]<=10) {
    		return true;
    	}
    	return false;
    }
    
    public Node getStartNode() {
		return this.start;
	}

	public Node getEndNode() {
		return this.end;
	}
    
    public String toString(){
        return "Walk from " + start.getName() + " to " + end.getName();
    }

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	
}


//Class definition for path 
class Path {
    private double cost;
    private int time;
    private ArrayList<Subpath> subpaths = new ArrayList<>();

    public void addPath(Subpath s){
        subpaths.add(s);
    }

    public double getTotalCost(){
    	cost = 0;
    	for (Subpath s: subpaths) {
    		cost += s.getCost();
    	}
        return cost;
    }

    public int getTotalTime(){
    	time = 0;
    	for (Subpath s: subpaths) {
    		time += s.getTime();
    	}
        return time;
    }

    public String toString(){
        String output = "";
        String timeStr = " | Total time: ";
        for (Subpath s : subpaths){
            output += s.toString() + "\n";
        }
        int hours = getTotalTime()/60;
        int minutes = getTotalTime()%60;
        if (hours >0) {
        	timeStr += hours + " hours and " + minutes + " minutes";
        }
        else {
        	timeStr += minutes + " minutes";
        }
        output += "Total cost: $" + String.format("%.2f",getTotalCost()) + timeStr;
        return output;
    }
    
    public void modifySmallestSubpath(int i) {
    	int index = -1;
    	int minVal = Integer.MAX_VALUE;
    	for (Subpath s: this.subpaths) {
    		if (s.getSubpathDistance()<minVal && !(s instanceof Walk)) {
    			if (i<= this.subpaths.size()) {
    				if (!s.isModified()) {
    					if (s.isReachableByPT() || s.isReachableByWalk()) {
    	    				minVal = s.getSubpathDistance();
    	    				index = this.subpaths.indexOf(s);
    	    				//System.out.println("Modyfying the path from " + s.getStartNode().getName() + " to " +
    	    				//s.getEndNode().getName());
    	    			}
    				}
    			}
    			else {
					minVal = s.getSubpathDistance();
    				index = this.subpaths.indexOf(s);
    				//System.out.println("Modyfying the path from " + s.getStartNode().getName() + " to " +
    				//s.getEndNode().getName());
    			}
    		}
    		
    	}
    	
    	
    	if (index != -1) {
    		Subpath temp1 = this.subpaths.get(index);
    		if (i<=this.subpaths.size()) {
    			if (temp1.isReachableByWalk()) {
            		Subpath m = new Walk(temp1.getStartNode(),temp1.getEndNode());
            		m.setModified(true);
            		this.subpaths.set(index, m);
            	}
            	else if (!(temp1 instanceof PT)){
            		//System.out.println(index);
            		Subpath m = new PT(temp1.getStartNode(),temp1.getEndNode());
            		m.setModified(true);
            		this.subpaths.set(index, m);
            	}
        	}
    		else {
        		Subpath m = new Walk(temp1.getStartNode(),temp1.getEndNode());
        		this.subpaths.set(index, m);
    		}
    	}
    }
    
    public void optimzePath(double excess) {
    	Path temp = new Path();
    	for (Subpath s: this.subpaths) {
    		temp.addPath(s);
    	}
    	
    	while (!temp.subpaths.isEmpty()) {
    		double maxVal = Integer.MIN_VALUE;
    		Subpath alt = null;
    		int index = -1;
    		
    		for (Subpath s: temp.subpaths) {
    			double taxiVal = Taxi.taxiCost[s.getStartNode().getId()][s.getEndNode().getId()];
    			if (taxiVal>maxVal && taxiVal<= excess) {
    				maxVal = taxiVal;
    				index = temp.subpaths.indexOf(s);
    				alt = s;
    			}
    		}
    		
    		if (index == -1) {
    			break;
    		}
    		else {
    			Subpath newPath = new Taxi(alt.getStartNode(),alt.getEndNode());
    			this.subpaths.set(index, newPath);
    			temp.subpaths.remove(alt);
    			excess -= maxVal;
    		}
    	}
    }
}

