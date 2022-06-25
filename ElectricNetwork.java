import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Comparator;


class Edge  {
	String start;
	String dest;
	int cost;
	
	public Edge (String start,String dest,int cost){
		this.start = start;
		this.dest = dest;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return start +"," + dest + "," + cost;
	}

}

class EdgeComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge e1, Edge e2) {
		int result = e1.cost<e2.cost? -1 : 1;
		return result;
	}

}


public class ElectricNetwork {

	public static  Hashtable<String,ArrayList<Edge>> createAdjacencyList(String network){
		Hashtable<String,ArrayList<Edge>> adjacencyList = new Hashtable <>();
		//"T1,T2,5;T1,T3,5;T1,T4,7;T2,T3,6;T2,T4,5;T3,T4,5;
		String [] networkEdges = network.split(";");
//		if(networkEdges.length == 1){
//			return null;
//		}
		for (int j = 0 ; j < networkEdges.length ;j++){
			String [] vertices = networkEdges[j].split(",");
			Edge e1 = new Edge(vertices[0],vertices[1],Integer.parseInt(vertices[2])); //t1
			Edge e2 = new Edge(vertices[1],vertices[0],Integer.parseInt(vertices[2])); //t2 opp edge
			
			
			
			if(!(adjacencyList.containsKey(vertices[0]))){
				ArrayList<Edge> edges = new ArrayList<>();
				edges.add(e1);
				adjacencyList.put(vertices[0],edges);
			}
			else{
				ArrayList<Edge> edges = adjacencyList.get(vertices[0]);
				//check if edge in list
				if(!(edges.contains(e1))){
				edges.add(e1);
				adjacencyList.put(vertices[0],edges);
				}
			}
			
			if(!(adjacencyList.containsKey(vertices[1]))){
				ArrayList<Edge> edges = new ArrayList<>();
				edges.add(e2);
				adjacencyList.put(vertices[1],edges);
			}
			else{
				ArrayList<Edge> edges = adjacencyList.get(vertices[1]);
				if(!(edges.contains(e1))){
				edges.add(e2);
				adjacencyList.put(vertices[1],edges);
				}
			}
		}
		return adjacencyList;
			
	}
	
	public static String minimumConnections (String network){
		
		Hashtable<String,ArrayList<Edge>> adjacencyList = createAdjacencyList(network);
//		if(adjacencyList  == null){
//			return network;
//		}
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(1, new EdgeComparator());
		
		int mstSize = (adjacencyList.size())-1;
		int count = 0;
		int cost = 0;
		Hashtable<String,Boolean> visited = new Hashtable<>();
		Edge [] mst = new Edge [mstSize];
		
		Enumeration<String> keys = adjacencyList.keys();
		String firstNode = keys.nextElement();
		visited.put(firstNode,true);
		while(keys.hasMoreElements()){
			String k = keys.nextElement();
			visited.put(k, false);
		}
		
		ArrayList<Edge> edgesfirstNode = adjacencyList.get(firstNode);
		
		//adding connected edges to start node
		for(int i = 0 ; i < edgesfirstNode.size() ; i++){
			if(!(visited.get(edgesfirstNode.get(i).dest))){
				pq.add(edgesfirstNode.get(i));
			}
		}
		
		while(!(pq.isEmpty())){
			if(count!=mstSize){
				Edge e = pq.poll();
				
				//System.out.println("line 127 "+e+ " ,count"+ count);
				if(!(visited.get(e.dest))){
					cost += e.cost;
					mst[count] = e;
					count++;
					//System.out.println("this is count "+count);
					ArrayList<Edge> edges = adjacencyList.get(e.dest);
					visited.put(e.dest, true);
					for(int i = 0 ; i < edges.size() ; i++){
						if(!(visited.get(edges.get(i).dest))){
							pq.add(edges.get(i));
							
						//System.out.println("line 135 "+edges.get(i)+ " ,count"+ count);
						}
					}
					
				}
			}else{
				break;
			}
		}
		String sol = "";
		for(int k = 0 ; k< mst.length;k++){
			if(k == mst.length -1){
				sol+= mst[k]+"";
			}
			else{
				sol+= mst[k]+";";
			}
			System.out.println("----"+mst[k]+"---");
		}
		return sol+";"+cost;

	}
	
	public static void print(String network){
		
		Iterator<ArrayList<Edge>> itr = createAdjacencyList(network).values().iterator();
		
		 
		while(itr.hasNext()){
		    ArrayList<Edge> arr = itr.next();
		    for(int i = 0 ; i< arr.size();i++){
		    	System.out.print(arr.get(i)+", ");
		    }
		    System.out.println();
		}
	}
	
	public static void main(String[] args) {
		String n = "T1,T2,5;T1,T3,5;T1,T4,7;T2,T3,6;T2,T4,5;T3,T4,5;";
		System.out.println("------------------"+minimumConnections(n)+"------------------");
		//print(n);
	}
}
