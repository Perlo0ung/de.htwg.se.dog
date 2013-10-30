package de.htwg.se.dog.models;

public class Field {
		
		private final int owner;
		private boolean house;
		private boolean blocked;
		private Figure figure;
		
		public Field(int owner) {
			this.blocked = false;
			if (owner == 0 ) {
				this.owner = 0;
				this.house = false;
			} else {
				this.owner = owner;
				this.house = true;
			}
		}
		public void putFigure(Figure f) {
			this.figure = f;
		}
		
		public Figure removeFigure(){
			Figure tmp = this.figure;
			this.figure = null;
			return tmp;
		}
		
		public Figure getFigure() {
			return this.figure;
		}
		
		public int getOwner() {
			return this.owner;
		}
		
		public void setBlocked(boolean a) {
			this.blocked = a;
		}
		
		public boolean getBlocked() {
			return this.blocked;
		}
		
		public boolean isHouse(){
			return this.house;
		}

}
