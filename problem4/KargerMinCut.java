package problem4;

import java.io.*;
import java.util.*;

class Vertex {
	private String val;
	private List <String> adjacents;

	public Vertex (String[] adjs) {
		this.val = adjs[0];
		adjacents = new ArrayList(adjs.length);

		for (int i=1; i<adjs.length; i++) 
			adjacents.add(adjs[i]);

	}

	public void setVal (String val) {
		this.val = val;	
	}
	
	public String getVal () {
		return val;
	}
	
	public List<String> getAdjacents () {
		return adjacents;
	}

	public String randomAdjacent () {
		Random random = new Random();
		return adjacents.get(random.nextInt(adjacents.size()));
	
	}
	
	public void addAdjacent(String adj) {
		adjacents.add(adj);
	}
	public void removeAdjacent (String adj) {
		adjacents.remove(adj);
	}
	
}

class Graph {
	private List<Vertex> vertices;

	public Graph() {
		vertices = new ArrayList();
	}
	public	Graph (File file) {
		this();

		String list;
		BufferedReader br;

		try {
			br = new BufferedReader (new FileReader (file));
			while ((list=br.readLine())!= null)
				this.append(new Vertex(list.split("\\s+")));						
		}
		catch (IOException ioe) {
			System.out.println (ioe);
		}
	}
	
	public List<Vertex> getVertices () {
		return this.vertices;
	}

	public void append (Vertex ... verts) {
		for (int i=0; i<verts.length; i++) 
			vertices.add(verts[i]);
	}
	
	private void updateAdjacentVertex (String val, String newAdj, String oldAdj) {
		for (Vertex v : vertices) {
			if (v.getVal().equals(val)) {
				v.removeAdjacent (oldAdj);
				v.addAdjacent (newAdj);
				return;
			}
		}
	}
			
	private void fuse (Vertex v1, Vertex v2) {
		List<String> adjacents1 = v1.getAdjacents();
		List<String> adjacents2 = v2.getAdjacents();
				
		for (String adj : adjacents2) {
			if (!adj.equals(v1.getVal())) { 
				adjacents1.add(adj);
				updateAdjacentVertex (adj, v1.getVal(), v2.getVal());
			}
		}
		
		while (adjacents1.remove(v2.getVal())) {}
		
	}
	
	public int kargerMinCut () {
		Random random = new Random();
		
		while (vertices.size() > 2) {
			Vertex	rVertex = vertices.get (random.nextInt(vertices.size()));
			String rAdjacent = rVertex.randomAdjacent ();
		
			for (Vertex v : vertices) {
				if ( v != rVertex && v.getVal().equals(rAdjacent) ) {
					fuse (rVertex, v);
					vertices.remove (v);
					break;
				}
			}
		}
		return vertices.get(0).getAdjacents().size();
	}

	@Override
	public String toString() {
		for (Vertex v : vertices) 
			System.out.println (v.getVal() + " " + v.getAdjacents());

		return null;
	}	
	
}

public class KargerMinCut {
	public static void main (String ... args) {
		File file = new File ("/Users/ahliddin/Dropbox/Study/Coursera/Algorithms1/problem4/kargerMinCut.txt");
		Graph g = new Graph(file);
		
		g.toString();
		
		int min = -1; 
		int tmp;
		
		//System.out.println ("min: " + new Graph(file).kargerMinCut());
		for (int i=0; i<100; i++) {
			tmp = new Graph(file).kargerMinCut();			
			if (min == -1)
				min = tmp;
			else if (tmp < min)
				min = tmp;
			System.out.print(tmp + " ");
		}
		System.out.println("\nmin: " + min);
		
	}

}
