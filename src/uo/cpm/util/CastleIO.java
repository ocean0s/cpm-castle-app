package uo.cpm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uo.cpm.model.Booking;
import uo.cpm.model.Castle;
import uo.cpm.model.Discount;

public class CastleIO {

	public static final String CASTLES_PATH = "files/castles.dat";
	public static final String DISCOUNTS_PATH = "files/discounts.dat";
	public static final String BOOKINGS_PATH = "files/bookings.dat";
	
	/**
	 * @param pathToTheFile
	 * @return BufferedReader for the file path provided
	 * @throws FileNotFoundException
	 */
	private BufferedReader openFileForReading(String pathToTheFile) throws FileNotFoundException {
		BufferedReader br = new BufferedReader (
				new FileReader(pathToTheFile));
		return br;
	}
	
	/**
	 * @param pathToTheFile
	 * @return BufferedWriter for the file path provided
	 * @throws FileNotFoundException
	 */
	private BufferedWriter openFileForWriting(String pathToTheFile) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(pathToTheFile, true));
		return bw;
	}
	
	/**
	 * @throws RuntimeException if file is not found or an IO error arises.
	 * Skips lines with errors
	 * @return list of castles generated with the data in the castles.dat file specified by the route in the class constant.
	 */
	public List<Castle> loadCastles(){
		BufferedReader br;
		List<Castle> res = new ArrayList<>();
		try {
			br = openFileForReading(CASTLES_PATH);
		String line;
		int index = 1;
			try {
				while ((line = br.readLine()) != null) {
					try {
						res.add(parseCastle(line, index));
					} catch (InvalidLineFormatException e) {
						index++;
						continue;
					}
					index++;
				}
			} finally {
				br.close();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("castle.dat file could not be found. " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Fatal error in input output. " + e.getMessage());
		} 
		return res;
	}

	/**
	 * @param line
	 * @param index
	 * @return castle parsed from the line provided
	 * @throws InvalidLineFormatException if an invalid line is detected
	 */
	private Castle parseCastle(String line, int index) {
		if (line == null || line.isBlank())
			throw new InvalidLineFormatException("Invalid line at position" + index + ". Line is blank. Parsing can not resume.");
		String[] parts = line.strip().split(";");
		if (parts.length != 6)
			throw new InvalidLineFormatException("Invalid line at position" + index + ". Invalid number of fields in line. Parsing can not resume.");
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].isBlank())
				throw new InvalidLineFormatException("Invalid line at position" + index + ". Some field (" + i + ") is blank. Parsing can not resume.");
		}
		String[] enchantments = parts[5].split("-");
		if (enchantments.length == 0 || enchantments.length > 6)
			throw new InvalidLineFormatException("Invalid line at position" + index + ". Enchantment format is invalid. Parsing can not resume.");
		int price;
		try {
			price = Integer.parseInt(parts[4]);
		} catch (NumberFormatException e) {
			throw new InvalidLineFormatException("Invalid line at position" + index + ". Price format is incorrect. Parsing can not resume.");
		}
		return new Castle(parts[0],parts[1],parts[2],parts[3], price, enchantments);
	}
	
	/**
	 * Writes the discount as a parameter associated with the ID provided to the discounts.dat file
	 * @throws IllegalArgumentException if parameters are null, blank or invalid.
	 * @throws RuntimeException if an IO error arises.
	 * @param ID		String
	 * @param discount	Discount
	 */
	public void writeDiscount(String ID, Discount discount) {
		if (ID == null || ID.isBlank())
			throw new IllegalArgumentException("ID is null or blank.");
		if (discount == null || discount == Discount.NO_DISCOUNT)
			throw new IllegalArgumentException("Invalid discount to save.");
		BufferedWriter bw;
		try {
			bw = openFileForWriting(DISCOUNTS_PATH);
			try {
				bw.write(ID + ";" + discount);
				bw.newLine();
			} finally {
				bw.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("Fatal error in input/output. " + e);
		}
	}
	
	/**
	 * Removes the line with a discount in discounts.dat file
	 * @param id
	 * @return true if successful, false otherwise
	 */
	public boolean removeDiscount(String id) {
		if (id == null || id.isBlank())
			throw new IllegalArgumentException("ID is null or blank.");	
		File discounts = new File(DISCOUNTS_PATH);
		File temp = new File("files/temp.dat");
		try {
	    BufferedReader reader = new BufferedReader(new FileReader(discounts)); 
	    BufferedWriter writer = new BufferedWriter(new FileWriter(temp)); 
	    String currentLine; 
	    while((currentLine = reader.readLine()) != null) { 
	     	String idToRemove = currentLine.trim().split(";")[0]; 
	        if (idToRemove.equals(id)) 
	        	continue; // skip discount to delete
	        writer.write(currentLine);
	        writer.newLine();
	    } 
	    writer.close();  
	    reader.close();  
		} catch (Exception e) {
			throw new RuntimeException("Fatal error in input/output. " + e.getMessage());
		}
		discounts.delete();
	    return temp.renameTo(discounts); 
	}
	
	/**
	 * @param ID	String
	 * @throws RuntimeException if an IO error arises.
	 * @throws IllegalArgumentException if ID parameter is null or blank.
	 * @return Discount associated with the ID provided as a parameter if it exists. Otherwise NO_DISCOUNT.
	 */
	public Discount readDiscount(String ID) {
		if (ID == null || ID.isBlank())
			throw new IllegalArgumentException("ID is null or blank");
		BufferedReader br;
		try {
			br = openFileForReading(DISCOUNTS_PATH);
			String line;
			try {
				while ((line = br.readLine()) != null) {
					String[] parts = line.split(";");
					if (parts.length != 2)
						continue;
					if (parts[0].equals(ID))
						return Discount.valueOf(parts[1]);
				}
			} finally {
				br.close();
			}
		} catch (FileNotFoundException e) {
			return Discount.NO_DISCOUNT;
		} catch (IOException e) {
			throw new RuntimeException("Fatal error in input/output. " + e.getMessage());
		}
		return Discount.NO_DISCOUNT;
	}
	
	/**
	 * Write the specified Booking to the bookings.dat file.
	 * @param booking	Booking
	 * @throws RuntimeException if an IO error arises.
	 * @throws IllegalArgumentException if parameter is null.
	 */
	public void writeBooking(Booking booking) {
		if (booking == null)
			throw new IllegalArgumentException("Booking is invalid.");
		BufferedWriter bw;
		try {
			bw = openFileForWriting(BOOKINGS_PATH);
			try {
				bw.write(booking.toFileFormatString());
				bw.newLine();
			} finally {
				bw.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("Fatal error in input/output. " + e);
		}
	}
}
