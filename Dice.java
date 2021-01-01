public class Dice {
	private final int sides;		// number of sides on dice
	private int topFace;			// number of sides on dice

	public Dice(int n) {			// construct a dice with n sides, pre-condition: n > 1
		sides = n > 1 ? n : 2; 		// ensure at least 2 sides
		topFace = (int) (Math.random() * sides) + 1;
	}


	public Dice() {					// construct a dice with 6 sides
		this(6); 					// invoke Dice constructor with value 6
	}

	public int topFace() {			// return current value of top face of dice
		return topFace;
	}

	public int roll() {				// roll the dice; returning the result
		topFace = (int) (Math.random() * sides) + 1;
		return topFace;
	}

	public String toString() {				// convert to a String data type value
		return getClass().getName() + "[sides = " + sides + ", topFace = " + topFace + "]";
	}

}