package uo.cpm.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.model.game.Game;
import uo.cpm.util.CastleIO;

public class BookingApp {

	private Game game = new Game();
	private CastleIO io = new CastleIO();
	private Discount currentDiscount = Discount.NO_DISCOUNT; // game
	private Castle selectedCastle = null;
	private Discount reservationDiscount = Discount.NO_DISCOUNT; // reservation
	private Booking booking;
	private String dniToConsume;

	/**
	 * @throws RuntimeException if file is not found or an IO error arises.
	 * Skips lines with errors
	 * @return list of castles generated with the data in the castles.dat file specified by the route in the class constant.
	 */
	public List<Castle> loadCastles() {
		return io.loadCastles();
	}
	
	/**
	 * @return total maximum number of plays of a game
	 */
	public int getMaxPlays() {
		return game.getMaxPlays();
	}

	/**
	 * @return integer array containing the sizes of each row in the game
	 */
	public int[] getSizes() {
		return game.getSizes();
	}

	/**
	 * @return String matrix containing the text representation of the different ghosts
	 */
	public String[][] getGameTexts() {
		return game.getGameTexts();
	}
	
	/**
	 * Performs a move in the internal board from the column passed as a parameter in the pyramidal board.
	 * @param origin column
	 * @return true if successful
	 */
	public boolean move(int gb) {
		return game.move(gb);
	}
	
	/**
	 * @return current throw in the progression of the game
	 */
	public int getCurrentThrow() {
		return game.getCurrentThrow();
	}
	
	/**
	 * Checks for game finish and if so, saves the discount obtained locally in the object.
	 * @return true if the game has finished (the number of plays remaining is 0)
	 */
	public boolean isGameFinished() {
		if (game.hasFinished())
			currentDiscount = game.getGameResultCode();
		return game.hasFinished();
	}

	/**
	 * @return string representation of the discount obtained
	 */
	public String getCurrentDiscountCode() {
		return currentDiscount.toString();
	}

	/**
	 * @return true if the discount is the maximum obtainable (EXTRA25)
	 */
	public boolean isMaxDiscount() {
		return currentDiscount == Discount.EXTRA25;
	}
	
	/**
	 * @return true if the user has obtained a discount in the game
	 */
	public boolean hasDiscount() {
		return currentDiscount != Discount.NO_DISCOUNT;
	}
	
	/**
	 * Rolls a die between 1 and 2 and places the result in the game
	 */
	public void roll() {
		game.roll();
	}

	/**
	 * @param row
	 * @param col
	 * @return argument coordinates have a ghostbuster
	 */
	public boolean hasGhostBuster(int row, int col) {
		return game.hasGhostBuster(row, col);
	}
	
	/**
	 * @return true if tha game has finished (the number of plays remaining is 0)
	 */
	public int getPlaysRemaining() {
		return game.getPlaysRemaining();
	}

	/**
	 * Initializes a new game and erases any discount obtained in a previous game not saved.
	 */
	public void initializeGame() {
		game = new Game();
		currentDiscount = Discount.NO_DISCOUNT;
	}
	
	/**
	 * Saves a discount if the dni is valid (and does not 
	 * have a discount already associated to it) and there 
	 * is a discount obtained through the game.
	 * @param dni
	 * @return true if the operation was successful
	 */
	public boolean saveDiscount(String dni) {
		if (dni == null || dni.isBlank() || currentDiscount == Discount.NO_DISCOUNT)
			return false;
		Discount d = io.readDiscount(dni);
		if (d == Discount.NO_DISCOUNT) {
			io.writeDiscount(dni, currentDiscount);
			currentDiscount = Discount.NO_DISCOUNT;
			return true;
		}
		return false;
	}

	/**
	 * @param maxPrice
	 * @param filters
	 * @return filtered list of valid filtered castles 
	 * according to the maximum price and enchantments provided.
	 */
	public List<Castle> getFilteredCastles(int maxPrice, List<String> filters) {
		List<Castle> base = loadCastles();
		List<Castle> filtered = new ArrayList<>();
		for (Castle c : base) {
			boolean flagged = false;
			if (c.getPricePerNight() > maxPrice)
				continue;
			for (String en : filters)
				if (!c.hasEnchantment(en)) {
					flagged = true;
					break;
				}
			if (!flagged)
				filtered.add(c);
		}
		return filtered;
	}

	/**
	 * Selects a castle for the reservation
	 * @param castle
	 */
	public void selectCastle(Castle castle) {
		this.selectedCastle = castle;
	}

	/**
	 * Resets the internal state of the app.
	 */
	public void reset() {
		selectedCastle = null;
		currentDiscount = Discount.NO_DISCOUNT;
		booking = null;
		dniToConsume = null;
		game = new Game();
	}
	
	/**
	 * @return selected castle for reservation
	 */
	public Castle getSelectedCastle() {
		return selectedCastle;
	}
	
	/**
	 * Retrieves and saves internally the discount (if exixtent) 
	 * associated to the dni provided.
	 * @param dni
	 * @return true if the operation was successful
	 */
	public boolean retrieveDiscountCode(String dni) {
		if (dni == null || dni.isBlank())
			return false;
		reservationDiscount = io.readDiscount(dni);
		if (reservationDiscount != Discount.NO_DISCOUNT) {
			dniToConsume = dni;
			return true;
		}
		return false;
	}
	
	/**
	 * @return String representation associated to the discount for the reservation
	 */
	public String getReservationDiscountString() {
		if (reservationDiscount == Discount.EXTRA10)
			return "-10%";
		else if (reservationDiscount == Discount.EXTRA25)
			return "-25%";
		else 
			return "No discount";
	}

	/**
	 * Calculated subtotal without discounts for the current reservation based on the 
	 * number of nights and rooms.
	 * @param nights
	 * @param rooms
	 * @return subtotal 
	 */
	public double calculateSubtotal(int nights, int rooms) {
		return Math.round(selectedCastle.getPricePerNight() * rooms * nights * 100.0) / 100.0;
	}

	/**
	 * Calculated total with discount for the current reservation based on the 
	 * number of nights and rooms.
	 * @param nights
	 * @param rooms
	 * @return total 
	 */
	public double calculateTotal(int nights, int rooms) {
		if (reservationDiscount == Discount.EXTRA10)
			return Math.round(calculateSubtotal(nights, rooms) * 0.9 * 100.0) / 100.0;
		else if (reservationDiscount == Discount.EXTRA25)
			return Math.round(calculateSubtotal(nights, rooms) * 0.75 * 100.0) / 100.0;
		else
			return calculateSubtotal(nights, rooms);
	}

	/**
	 * Creates and saves internally an instance of a booking object for further wirting
	 * if formalized
	 * @param dni
	 * @param nameAndSurname
	 * @param email
	 * @param date
	 * @param nights
	 * @param rooms
	 * @param comments
	 * @throws IllegalArgumentException if any parameter is null or invalid
	 */
	public void prepareBooking(String dni, String nameAndSurname, String email, String date, int nights, int rooms,
			String comments) {
		if (dni == null || nameAndSurname == null || email == null 
				|| date == null || comments == null || nights <= 0 || rooms <= 0)
			throw new IllegalArgumentException("Some parameters are null or invalid.");
		booking = new Booking(dni, nameAndSurname, email, selectedCastle.getCode(), date, nights,
				rooms, calculateTotal(nights, rooms), comments);
	}
	
	/**
	 * @return associated booking for the reservation
	 */
	public Booking getBooking() {
		return booking;
	}
	
	/**
	 * Formalizes the booking erasing the discount associated to the dni if exixtent and 
	 * writing the booking to the booking file.
	 */
	public void formalize() {
		if (dniToConsume != null && !dniToConsume.isBlank() && io.removeDiscount(dniToConsume))
			dniToConsume = null;
		io.writeBooking(booking);
	}

	/**
	 * Resets the internal instance of the reservation booking.
	 */
	public void resetBooking() {
		booking = null;
	}
}
