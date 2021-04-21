import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class CompetitionTests {

	@Test
	public void testDijkstraConstructor() {
		String filename = "tinyEWD.txt";
		int sA = 60,sB=80, sC=50;
		CompetitionDijkstra dj = new CompetitionDijkstra(filename, sA, sB, sC);
		assertEquals(38, dj.timeRequiredforCompetition());
	}

	@Test
	public void testFWConstructor() {
		String filename = "tinyEWD.txt";
		int sA = 60, sB=80,sC=50;
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall(filename, sA, sB, sC);
		assertEquals(38, cfw.timeRequiredforCompetition());
	}

	@Test
	public void testSpeedWrong() {
		String filename = "tinyEWD.txt";
		int sA = 50, sB=100, sC=150;
		CompetitionDijkstra dj = new CompetitionDijkstra(filename, sA, sB, sC);
		assertEquals(-1, dj.timeRequiredforCompetition());
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall(filename, sA, sB, sC);
		assertEquals(-1, cfw.timeRequiredforCompetition());

	}

	@Test
	public void testFilenameWrong() {
		String filename = "tinyE.txt";
		int sA = 60, sB=80, sC=50;
		CompetitionDijkstra dj = new CompetitionDijkstra(filename, sA, sB, sC);
		assertEquals(-1, dj.timeRequiredforCompetition());
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall(filename, sA, sB, sC);
		assertEquals(-1, cfw.timeRequiredforCompetition());
	}
	@Test
	public void testError() {
		String filename = "input-A.txt";
		int sA = 50, sB=50, sC=50;
		CompetitionDijkstra dj = new CompetitionDijkstra(filename, sA, sB, sC);
		assertEquals(-1, dj.timeRequiredforCompetition());
		CompetitionFloydWarshall cfw= new CompetitionFloydWarshall(filename, sA, sB, sC);
		assertEquals(-1, cfw.timeRequiredforCompetition());
	}

	@Test
	public void testError2() {
		String filename = "input-C.txt";
		int sA = 50, sB=100, sC=100;
		CompetitionDijkstra dj = new CompetitionDijkstra(filename, sA, sB, sC);
		assertEquals(-1, dj.timeRequiredforCompetition());
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall(filename, sA, sB, sC);
		assertEquals(-1, cfw.timeRequiredforCompetition());

	}
	
	@Test
	public void testTooLarge() {
		String filename = "tinyEWD.txt";
		int sA = 200, sB=110, sC=140;
		CompetitionDijkstra dj = new CompetitionDijkstra(filename, sA, sB, sC);
		assertEquals(-1, dj.timeRequiredforCompetition());
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall(filename, sA, sB, sC);
		assertEquals(-1, cfw.timeRequiredforCompetition());
	}
	
	@Test
	public void testTooSmall() {
		String filename = "tinyEWD.txt";
		int sA=10, sB=20, sC=30;
		CompetitionDijkstra dj = new CompetitionDijkstra(filename, sA, sB, sC);
		assertEquals(-1, dj.timeRequiredforCompetition());
		CompetitionFloydWarshall cfw = new CompetitionFloydWarshall(filename, sA, sB, sC);
		assertEquals(-1, cfw.timeRequiredforCompetition());
	}
}