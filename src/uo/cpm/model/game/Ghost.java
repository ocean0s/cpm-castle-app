package uo.cpm.model.game;

public class Ghost extends Entity {

	private char squad;
	
	public Ghost(char squad) {
		this.squad = squad;
	}
	
	public char getSquad() {
		return squad;
	}
	
	public String toString() {
		return ""+squad;
	}
}
