import java.io.*;
import java.util.*;

class Node {
	int idx, cost;

	public Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

public class Main {
	//정점 개수, 간선 개수, 시작 정점 번호
	static int V, E, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
		
		for (int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<>());
		}
		
		int r, c, cost;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			graph.get(r).add(new Node(c, cost));
		}
		
		boolean[] visited = new boolean[V+1];
		int[] dist = new int[V+1];
		
		for (int i = 0; i < V+1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[K] = 0;
		
		for (int i = 0; i < V; i++) {
			int nodeValue = Integer.MAX_VALUE;
			int nodeIdx = 0;
			for (int j = 1; j < V + 1; j++) {
				if (!visited[j] && dist[j] < nodeValue) {
					nodeValue = dist[j];
					nodeIdx = j;
				}
			}
			visited[nodeIdx] = true;
			
			for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
				Node adjNode = graph.get(nodeIdx).get(j);
				
				if(dist[adjNode.idx] > dist[nodeIdx] + adjNode.cost) {
					dist[adjNode.idx] = dist[nodeIdx] + adjNode.cost;
				}
			}
		}
		
		for (int i = 1; i < V+1; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(dist[i]);
			}
		}
		
	}

}
