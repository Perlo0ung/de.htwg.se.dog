package de.htwg.se.dog.controller;
import de.htwg.se.dog.models.*;

public class Movement {
		
	public boolean moveFigure(Gamefield gamefield, int steps, int startfieldnr){
		Field[] array = gamefield.getGamefield();
		if(!vaildMove(gamefield, steps, startfieldnr)){
			return false;
		}
		
		
		
		return true;
	}
	
	
	public boolean vaildMove(Gamefield gamefield, int steps, int startfieldnr){
		if(getTargetfield(gamefield, steps, startfieldnr) >= 0){
			return true;
		}
		return false;
	}
	
	public int getTargetfield(Gamefield gamefield, int steps, int startfieldnr){
		Field[] array = gamefield.getGamefield();
		int counter = steps;
		int playerNr = array[startfieldnr].getFigure().getOwner();
		int currentfieldID = (startfieldnr+1) % gamefield.getFieldSize();

		while(counter > 0){
			int nextfieldID = (currentfieldID+1) % gamefield.getFieldSize();
			int currentFieldOwner = array[currentfieldID].getOwner();
			if(currentFieldOwner == 0){
				counter--;
			} else if (currentFieldOwner == playerNr){
				counter--;
				if(counter > 0 && array[nextfieldID].getOwner() != playerNr){
					counter += gamefield.getHouseCount();
				}
			}
			if(counter > 0){
				currentfieldID = nextfieldID;	
			}
		}
		return currentfieldID;
	}

}
