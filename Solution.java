import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


/*
 * 
 */
class Node {
	
	// **** value for this node ****
	public char	val;
	
	// **** list of adjacent (child) nodes to this node ****
	LinkedList<Node> adjacent = new LinkedList<Node>();

	
	// **** constructor ****
	public Node(char val) {
		this.val = val;
	}

	
	// **** ****
	@Override
	public String toString() {

		// **** ****
		StringBuilder sb = new StringBuilder();
		
		// **** ****
		sb.append("val: " + this.val + " adjacency: ");
		
		// **** ****
		for (Node n : this.adjacent) {
			sb.append(n.val + " ");
		}
		
		// **** ****
		return sb.toString();
	}
}


/*
 * 
 */
class Graph {
	
	// **** ****
	private HashMap<Character, Node> graphNodes = new HashMap<Character, Node>();
	
	
	// **** add node to graph ****
	public Node addNode(char val) {
		
		// **** lookup the node in the graph ****
		if (graphNodes.containsKey(val))
			return graphNodes.get(val);
		
		// **** instantiate a new node ****
		Node node = new Node(val);
		
		// **** add the node to the graph ****
		graphNodes.put(val, node);
		
		// **** return the node ****
		return node;
	}

	
	// **** get the node from the graph ****
	private Node getNode(char val) {
		return graphNodes.get(val);
	}
	
	
	// **** add edge between nodes in the graph ****
	public boolean addEdge(String edge) {
		
		// **** split string ****
		String[] arr 	= edge.split("-");
		char src 		= arr[0].charAt(0);
		char dst 		= arr[1].charAt(0);
		
		// **** get the source node ****
		Node s = getNode(src);
		if (s == null)
			return false;
		
		// **** get the destination node ****
		Node d = getNode(dst);
		if (d == null)
			return false;
		
		// **** add edge from s to d ****
		s.adjacent.add(d);
		
//		// **** add edge from d to s ****
//		d.adjacent.add(s);
		
		// **** ****
		return true;
	}

	
	// **** ****
	public boolean hasPathDFS(String fromTo) {
		
		// **** ****
		String[] arr 	= fromTo.split("-");
		char src 		= arr[0].charAt(0);
		char dst 		= arr[1].charAt(0);
		
		// **** ****
		Node source = getNode(src);
		if (source == null)
			return false;
		
		Node destination = getNode(dst);
		if (destination == null)
			return false;
		
		// **** to avoid revisiting nodes and getting into an infinite loop ****
		HashSet<Character> visited = new HashSet<Character>();
		
		// **** recursive method ****
		return hasPathDFS(source, destination, visited);
	}
	
	
	// **** DFS implementation (recursive and requires visited flag) ****
	private boolean hasPathDFS(Node source, Node destination, HashSet<Character> visited) {
		
		// **** check if this node has already been visited ****
		if (visited.contains(source.val))
			return false;
		
		// **** flag this node has been visited ****
		visited.add(source.val);
		
		// **** check if we found a path ****
		if (source == destination)
			return true;
				
		// **** check all children of this node ****
		for (Node child : source.adjacent) {
			if (hasPathDFS(child, destination, visited))
				return true;
		}
		
		// **** no path was found ****
		return false;
	}
	
	
	// **** ****
	public boolean hasPathBFS(String fromTo) {
		
		// **** ****
		String[] arr 	= fromTo.split("-");
		char src 		= arr[0].charAt(0);
		char dst 		= arr[1].charAt(0);
		
		// **** ****
		Node source = getNode(src);
		if (source == null)
			return false;
		
		Node destination = getNode(dst);
		if (destination == null)
			return false;
	
		// **** ****
		return hasPathBFS(source, destination);
	}
	
	
	// **** BFS implementation (uses queue and requires visited flag) ****
	private boolean hasPathBFS(Node source, Node destination) {
		
		// **** ****
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		
		// ???? to avoid revisiting nodes and getting into an infinite loop ????
		HashSet<Character> visited = new HashSet<Character>();
	
		// **** start with this node ****
		nextToVisit.add(source);
		
		// **** visit next nodes ****
		while (!nextToVisit.isEmpty()) {
			
			// **** get the node being visited ****
			Node node = nextToVisit.pop();
			
			// **** check if we are done ****
			if (node == destination)
				return true;
			
			// **** check if we have visited this node ****
			if (visited.contains(node.val))
				continue;
			
			// **** flag we have visited this node *****
			visited.add(node.val);
			
			// **** visit the children of this node ****
			for (Node child : node.adjacent) {
				nextToVisit.add(child);
			}
		}

		// **** ****
		return false;
	}
}


/*
 * Test scaffolding.
 */
public class Solution {	
	
	
	// **** main entry point ****
	public static void main(String[] args) {

		// **** ****
		Node n 		= null;
		String edge = "";

		// **** instantiate graph ****
		Graph g = new Graph();
		
		// **** add nodes to our graph ****
		n = g.addNode('G');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('R');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('A');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('P');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('H');
		System.out.println("main <<< addNode: " + n.toString());
		
		n = g.addNode('D');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('F');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('S');
		System.out.println("main <<< addNode: " + n.toString());
		
		n = g.addNode('B');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('F');
		System.out.println("main <<< addNode: " + n.toString());
		n = g.addNode('S');
		System.out.println("main <<< addNode: " + n.toString());
		System.out.println();
		
		// **** add edges to the graph ****
		boolean added;
		
		edge = "X-Y";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);

		edge = "G-Y";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);

		edge = "X-G";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		System.out.println();
		
		edge = "G-R";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "R-A";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "A-P";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "A-S";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "P-H";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "S-H";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "G-B";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "B-D";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "B-F";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "D-F";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		
		edge = "F-S";
		added = g.addEdge(edge);
		System.out.println("main <<< addEdge " + edge + ": " + added);
		System.out.println();
		
		
		// **** DFS ****
		boolean path;
		String fromTo;
		
		fromTo = "X-Y";
		path = g.hasPathDFS(fromTo);
		System.out.println("main <<< hasPathDFS " + fromTo + ": " + path);
		
		fromTo = "G-Y";
		path = g.hasPathDFS(fromTo);
		System.out.println("main <<< hasPathDFS " + fromTo + ": " + path);
		
		fromTo = "X-G";
		path = g.hasPathDFS(fromTo);
		System.out.println("main <<< hasPathDFS " + fromTo + ": " + path);
		System.out.println();
		
		// **** to ****
		fromTo = "G-H";
		path = g.hasPathDFS(fromTo);
		System.out.println("main <<< hasPathDFS " + fromTo + ": " + path);
	
		// **** back  ****
		fromTo = "H-G";
		path = g.hasPathDFS(fromTo);
		System.out.println("main <<< hasPathDFS " + fromTo + ": " + path);
		System.out.println();
		
		// **** to ****
		fromTo = "G-S";
		path = g.hasPathDFS(fromTo);
		System.out.println("main <<< hasPathDFS " + fromTo + ": " + path);
	
		// **** to ****
		fromTo = "S-G";
		path = g.hasPathDFS(fromTo);
		System.out.println("main <<< hasPathDFS " + fromTo + ": " + path);
		System.out.println();
		
		
		// **** BFS ****
		fromTo = "X-Y";
		path = g.hasPathBFS(fromTo);
		System.out.println("main <<< hasPathBFS " + fromTo + ": " + path);
		
		fromTo = "G-Y";
		path = g.hasPathBFS(fromTo);
		System.out.println("main <<< hasPathBFS " + fromTo + ": " + path);
		
		fromTo = "X-G";
		path = g.hasPathBFS(fromTo);
		System.out.println("main <<< hasPathBFS " + fromTo + ": " + path);
		System.out.println();
		
		// **** to ****
		fromTo = "G-H";
		path = g.hasPathBFS(fromTo);
		System.out.println("main <<< hasPathBFS " + fromTo + ": " + path);
	
		// **** back  ****
		fromTo = "H-G";
		path = g.hasPathBFS(fromTo);
		System.out.println("main <<< hasPathBFS " + fromTo + ": " + path);
		System.out.println();
		
		
		// **** ****
		fromTo = "G-S";
		path = g.hasPathBFS(fromTo);
		System.out.println("main <<< hasPathBFS " + fromTo + ": " + path);
	
		// **** to ****
		fromTo = "S-G";
		path = g.hasPathBFS(fromTo);
		System.out.println("main <<< hasPathBFS " + fromTo + ": " + path);

	}

}
