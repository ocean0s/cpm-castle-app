package uo.cpm.model;


public class Booking {

	private String ID;
	private String nameAndSurname;
	private String email;
	private String castleCode;
	private String date;
	private int days;
	private int numRooms;
	private double price;
	private String comment;
	
	/**
	 * Constructor for Booking objects based on the parameters.
	 * @param iD
	 * @param nameAndSurname
	 * @param email
	 * @param castleCode
	 * @param date
	 * @param days
	 * @param numRooms
	 * @param price
	 * @param comment
	 * @throws IllegalArgumentException if some parameter is null or blank apart from the comment.
	 */
	public Booking(String iD, String nameAndSurname, String email, String castleCode, String date, int days, int numRooms,
			double price, String comment) {
		setID(iD);
		setNameAndSurname(nameAndSurname);
		setEmail(email);
		setCastleCode(castleCode);
		setDate(date);
		setDays(days);
		setNumRooms(numRooms);
		setPrice(price);
		setComment(comment);
	}
	
	/**
	 * @return file format representation of the booking for writing
	 */
	public String toFileFormatString() {
		return ID + ";" + nameAndSurname + ";" + email + ";" + castleCode + ";"
				+ date + ";" + days + ";" + numRooms + ";" + price + ";" + comment;
	}

	public String getID() {
		return ID;
	}

	private void setID(String iD) {
		if (iD == null || iD.isBlank())
			throw new IllegalArgumentException("Invalid ID.");
		
		ID = iD;
	}

	public String getNameAndSurname() {
		return nameAndSurname;
	}

	private void setNameAndSurname(String nameAndSurname) {
		if (nameAndSurname == null || nameAndSurname.isBlank())
			throw new IllegalArgumentException("Invalid name and surname.");
		this.nameAndSurname = nameAndSurname;
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		if (email == null || email.isBlank())
			throw new IllegalArgumentException("Invalid email.");
		this.email = email;
	}

	public String getCastleCode() {
		return castleCode;
	}

	private void setCastleCode(String castleCode) {
		if (castleCode == null || castleCode.isBlank())
			throw new IllegalArgumentException("Invalid castle code.");
		this.castleCode = castleCode;
	}

	public String getDate() {
		return date;
	}

	private void setDate(String date) {
		if (date == null || date.isBlank())
			throw new IllegalArgumentException("Invalid date.");
		this.date = date;
	}

	public int getDays() {
		return days;
	}

	private void setDays(int days) {
		if (days <= 0)
			throw new IllegalArgumentException("Invalid number of days.");
		this.days = days;
	}

	public int getNumRooms() {
		return numRooms;
	}

	private void setNumRooms(int numRooms) {
		if (numRooms <= 0)
			throw new IllegalArgumentException("Invalid number of rooms.");
		this.numRooms = numRooms;
	}

	public double getPrice() {
		return price;
	}

	private void setPrice(double price) {
		if (price < 0)
			throw new IllegalArgumentException("Invalid price.");
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	private void setComment(String comment) {
		if (comment == null)
			throw new IllegalArgumentException("Invalid comment.");
		this.comment = comment;
	}

}
