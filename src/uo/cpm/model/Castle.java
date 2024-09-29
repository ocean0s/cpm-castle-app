package uo.cpm.model;

public class Castle {

	private String code;
	private String name;
	private String description;
	private String location;
	private int pricePerNight;
	private String[] enchantments;
	
	/**
	 * Creates a castle object based on the passed arguments.
	 * @param imageCode
	 * @param name
	 * @param description
	 * @param location
	 * @param pricePerNight
	 * @param enchantments
	 * @throws IllegalArgumentException if some parameter is null or invalid.
	 */
	public Castle(String imageCode, String name, String description, 
			String location, int pricePerNight, String[] enchantments) {
		setDescription(description);
		setEnchantments(enchantments);
		setImageCode(imageCode);
		setLocation(location);
		setName(name);
		setPricePerNight(pricePerNight);
	}
	
	public String getCode() {
		return code;
	}
	private void setImageCode(String imageCode) {
		if (imageCode == null)
			throw new IllegalArgumentException("Image code is invalid.");
		this.code = imageCode;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Name is invalid.");
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	private void setDescription(String description) {
		if (description == null)
			throw new IllegalArgumentException("Description is invalid.");
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	private void setLocation(String location) {
		if (location == null)
			throw new IllegalArgumentException("Location is invalid.");
		this.location = location;
	}
	public int getPricePerNight() {
		return pricePerNight;
	}
	private void setPricePerNight(int pricePerNight) {
		if (pricePerNight < 0)
			throw new IllegalArgumentException("Price per night is invalid.");
		this.pricePerNight = pricePerNight;
	}
	public String[] getEnchantments() {
		return enchantments;
	}
	private void setEnchantments(String[] enchantments) {
		if (enchantments == null || enchantments.length == 0 || enchantments.length > 6)
			throw new IllegalArgumentException("Enchantments are invalid.");
		for (String e : enchantments)
			if (e == null || e.isBlank())
				throw new IllegalArgumentException("Enchantments are invalid.");				
		this.enchantments = enchantments;
	}

	public String toString() {
		return getLocation() + "  -  " + getName();
	}
	
	/**
	 * @return legible String representation of the enchantments of the castle.
	 */
	public String getEnchantmentsString() {
		String res = "";
		for (String s : enchantments) {
			switch (s) {
			case "Ap":
				res += "Apparitions of ghosts";
				break;
			case "De":
				res += "Drop in temperature";
				break;
			case "En":
				res += "Lights blinking";
				break;
			case "Ob":
				res += "Moving objects";
				break;
			case "Ol":
				res += "Nauseous smells";
				break;
			case "Ru":
				res += "Strange noises";
				break;
			default:
				res += "Unknown";
				break;
			}
			res += ", ";
		}
		res = res.substring(0, res.length()-2) + ".";
		return res;
	}

	/**
	 * @param enchantment
	 * @return true if the castle has the enchantment denoted by the code provided as a parameter.
	 */
	public boolean hasEnchantment(String en) {
		for (String s : enchantments)
			if (s.equals(en))
				return true;
		return false;
	}
	
}
