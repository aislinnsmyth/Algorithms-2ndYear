public class Wallet {
	
	private double cash;		// current cash in wallet

	public Wallet() {			// construct wallet with zero cash
		cash = 0.0;
	}

	public void put(double money) {		// put an amount of money into wallet
		if (money > 0.0)				// precondition: money > 0.0
			cash = cash + money;
	}

	public boolean get(double money) {			// get an amount of money from wallet
		if (money > 0.0 && cash >= money) {		// pre-condition: money > 0.0
			cash = cash - money;				// returns true if wallet had enough cash, false otherwise
			return true;
		}
		return false;
	}

	public double check() {			// return current amount of cash in wallet
		return cash;
	}

	public String toString() {		// convert to a String data type value
		return getClass().getName() + "[cash = " + cash + "]";
	}

}