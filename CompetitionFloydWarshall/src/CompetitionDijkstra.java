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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	public int speedA;
	public int speedB;
	public int speedC;
	public int N;
	public int S;
	public double distTo[][];

	/**
	 * @param filename: A filename containing the details of the distTo road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */
	CompetitionDijkstra (String filename, int sA, int sB, int sC){
		this.speedA = sA;				//initialize speed variables inside constructor
		this.speedB = sB;
		this.speedC = sC;

		mapFile(filename);					//calling the function which breaks down the map
		timeRequiredforCompetition();		//calling the time required function 
	}


	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){

		if((speedA < 50 || speedA > 100) || (speedB < 50 || speedB > 100) || (speedC < 50 || speedC > 100)) {			//if statements to return -1 i 
			return -1;
		}
		
		//dijkstra algorithm
		for (int i = 0; i < distTo.length; i++) {
			boolean[] intersection = new boolean[distTo.length];
			intersection[i] = true;
			while (true) {
				int a = -1;
				for (int j = 0; j < distTo.length; j++)
					if ((intersection[j] == false) && (distTo[i][j] != Integer.MAX_VALUE)) {
						a = j;
						break;
					}
				if (a == -1)
					break;
				intersection[a] = true;
				for (int j = 0; j < distTo.length; j++)
					if (distTo[i][a] + distTo[a][j] < distTo[i][j]) {
						distTo[i][j] = distTo[i][a] + distTo[a][j];
						intersection[j] = false;
					}
			}
		}

		int minSpeed = Math.min(speedC, Math.min(speedA, speedB));
		double maxDistance = 0.0;
		for (int i = 0; i < distTo.length; i++)
			for (int j = 0; j < distTo[i].length; j++)
				if (distTo[i][j] == Integer.MAX_VALUE)
					return -1;
				else if (distTo[i][j] > maxDistance)
					maxDistance = distTo[i][j];
		if (maxDistance == 0)
			return -1;
		int maxTime = (int) Math.ceil(maxDistance * 1000 / minSpeed);
		System.out.println(maxTime);
		return maxTime;
	}


	private void mapFile(String filename) {
		try
		{
			if(filename != null) {
				File inputFile = new File(filename);
				if(inputFile != null) {
					Scanner file = new Scanner(inputFile);
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