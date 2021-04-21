import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * distTo intersections. In order to win, the three contestants need all to meet at any intersection
 * of the distTo as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The distTo is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the distTo.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 * 
 * @author: Aislinn Smyth
 */

public class CompetitionFloydWarshall {
	public int N;					//amount of intersections
	public int S;					//total number of streets
	public int speedA;				//average speed of person A
	public int speedB;				//average speed of person B
	public int speedC;				//average speed of person C
	public double distTo[][];		//matrix distance to
	public double maxDistance;		//max distance to

	/**
	 * @param filename: A filename containing the details of the distTo road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */
	CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
		this.speedA = sA;								//initialize the average speeds per person in constructor
		this.speedB = sB;
		this.speedC = sC;

		mapFile(filename);							//calling function mapFile which reads the information in the map
		timeRequiredforCompetition();

	}


	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){
		//returning -1 if the average speeds are outside the requirements
		if((speedA < 50 || speedA > 100) || (speedB < 50 || speedB > 100) || (speedC < 50 || speedC > 100)) {			//if condition to return -1 if the speed times are outside of the given domain
			return -1;
		}
				
		int V = distTo.length;								// initialize vertex length to use in for loops
		//System.out.println(distTo.length);

		//Floyd-Warshall algorithm shortest paths
		for(int k=0; k<V; k++) {											
			for(int i=0; i<V; i++) {
				for(int j=0; j<V; j++) {
					if (distTo[i][j] > distTo[i][k] + distTo[k][j])
						distTo[i][j] = distTo[i][k] + distTo[k][j];
				}
			}
		}
		//generating the max speed it will take to reach the intersections
		int minSpeed = Math.min(speedC, Math.min(speedA, speedB));
		double maxDistance = 0.0;
		for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
				if (distTo[i][j] == Integer.MAX_VALUE)
					return -1;
				else if (distTo[i][j] > maxDistance)
					maxDistance = distTo[i][j];
		if (maxDistance == 0)
			return -1;
		int maxTime = (int) Math.ceil(maxDistance * 1000 / minSpeed);
		return maxTime;
	}



	private void mapFile(String filename) {
		try
		{

			if(filename != null) {
				File inputFile = new File(filename);
				Scanner file = new Scanner(inputFile);
				if(inputFile != null) {
					if(file != null) {
						N = Integer.parseInt(file.nextLine());		
						S = Integer.parseInt(file.nextLine());
						distTo = new double[N][N];

						for(int i=0; i<distTo.length; i++) {
							for(int j=0; j<distTo.length; j++) {
								distTo[i][j] = Integer.MAX_VALUE;
								if(i==j) 
									distTo[i][j] = 0;
							}
						}

						while((file.hasNextLine())) {
							String[] input = file.nextLine().trim().split("\\s+");
							distTo[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = Double.parseDouble(input[2]);
						}
						file.close();
					}
				}
			}

	} catch (IOException e) {
		distTo = new double[0][0];
	}
}


}