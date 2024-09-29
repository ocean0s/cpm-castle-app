package uo.cpm.service;

import java.util.List;

import uo.cpm.model.Booking;
import uo.cpm.model.BookingApp;
import uo.cpm.model.Castle;

/**
 * FAÇADE
 */
public class CastleManager {
	
	private BookingApp app = new BookingApp();
	
	/**
	 * @throws RuntimeException if file is not found or an IO error arises.
	 * Skips lines with errors
	 * @return list of castles generated with the data in the castles.dat file specified by the route in the class constant.
	 */
	public List<Castle> loadCastles(){
		return app.loadCastles();
	}
	
	/**
	 * @return total maximum number of plays of a game
	 */
	public int getMaxPlays() {
		return app.getMaxPlays();
	}

	/**
	 * @return integer array containing the sizes of each row in the game
	 */
	public int[] getSizes() {
		return app.getSizes();
	}

	/**
	 * @return String matrix containing the text representation of the different ghosts
	 */
	public String[][] getGameTexts() {
		return app.getGameTexts();
	}
	
	/**
	 * Performs a move in the internal board from the column passed as a parameter in the pyramidal board.
	 * @param origin column
	 * @return true if successful
	 */
	public boolean move(int gb) {
		return app.move(gb);
	}
	
	/**
	 * @return current throw in the progression of the game
	 */
	public int getCurrentThrow() {
		return app.getCurrentThrow();
	}
	
	/**
	 * Checks for game finish and if so, saves the discount obtained locally in the object.
	 * @return true if the game has finished (the number of plays remaining is 0)
	 */
	public boolean isGameFinished() {
		return app.isGameFinished();
	}
	
	/**
	 * @return string representation of the discount obtained
	 */
	public String getCurrentDiscountCode() {
		return app.getCurrentDiscountCode();
	}

	/**
	 * @return true if the discount is the maximum obtainable (EXTRA25)
	 */
	public boolean isMaxDiscount() {
		return app.isMaxDiscount();
	}
	
	/**
	 * @return true if the user has obtained a discount in the game
	 */
	public void roll() {
		app.roll();
	}

	/**
	 * Rolls a die between 1 and 2 and places the result in the game
	 */
	public boolean hasGhostBuster(int row, int col) {
		return app.hasGhostBuster(row,col);
	}
	
	/**
	 * @param row
	 * @param col
	 * @return argument coordinates have a ghostbuster
	 */
	public int getPlaysRemaining() {
		return app.getPlaysRemaining();
	}
	
	/**
	 * @return true if tha game has finished (the number of plays remaining is 0)
	 */
	public boolean hasDiscount() {
		return app.hasDiscount();
	}

	/**
	 * Initializes a new game and erases any discount obtained in a previous game not saved.
	 */
	public void initializeGame() {
		app.initializeGame();
	}

	/**
	 * Saves a discount if the dni is valid (and does not 
	 * have a discount already associated to it) and there 
	 * is a discount obtained through the game.
	 * @param dni
	 * @return true if the operation was successful
	 */
	public boolean saveDiscount(String dni) {
		return app.saveDiscount(dni);
	}

	/**
	 * @param maxPrice
	 * @param filters
	 * @return filtered list of valid filtered castles 
	 * according to the maximum price and enchantments provided.
	 */
	public List<Castle> getFilteredCastles(int maxPrice, List<String> filters) {
		return app.getFilteredCastles(maxPrice,filters);
	}

	/**
	 * Selects a castle for the reservation
	 * @param castle
	 */
	public void selectCastle(Castle castle) {
		app.selectCastle(castle);
	}

	/**
	 * Resets the internal state of the app.
	 */
	public void reset() {
		app.reset();
	}
	
	/**
	 * @return selected castle for reservation
	 */
	public Castle getSelectedCastle() {
		return app.getSelectedCastle();
	}
	
	/**
	 * Retrieves and saves internally the discount (if exixtent) 
	 * associated to the dni provided.
	 * @param dni
	 * @return true if the operation was successful
	 */
	public boolean retrieveDiscountCode(String dni) {
		return app.retrieveDiscountCode(dni);
	}
	
	/**
	 * @return String representation associated to the discount for the reservation
	 */
	public String getReservationDiscountString() {
		return app.getReservationDiscountString();
	}

	/**
	 * Calculated subtotal without discounts for the current reservation based on the 
	 * number of nights and rooms.
	 * @param nights
	 * @param rooms
	 * @return subtotal 
	 */
	public double calculateSubtotal(int nights, int rooms) {
		return app.calculateSubtotal(nights, rooms);
	}

	/**
	 * Calculated total with discount for the current reservation based on the 
	 * number of nights and rooms.
	 * @param nights
	 * @param rooms
	 * @return total 
	 */
	public double calculateTotal(int nights, int rooms) {
		return app.calculateTotal(nights, rooms);
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
		app.prepareBooking(dni, nameAndSurname, email, date, nights, rooms, comments);
	}
	
	/**
	 * @return associated booking for the reservation
	 */
	public Booking getBooking() {
		return app.getBooking();
	}

	/**
	 * Resets the internal instance of the reservation booking.
	 */
	public void resetBooking() {
		app.resetBooking();
	}
	
	/**
	 * Formalizes the booking erasing the discount associated to the dni if exixtent and 
	 * writing the booking to the booking file.
	 */
	public void formalize() {
		app.formalize();
	}
}
