import java.util.ArrayList;

public class Vehicle {
	
	private int id;
	private int posX;
	private int posY;
	
	private int step;
	
	private ArrayList<Ride> rides;
	
	private Ride currentRide;
	
	public Vehicle(int id) {
		this.id = id;
		this.posX = 0;
		this.posY = 0;
		this.step = 0;
		this.rides = new ArrayList<>();
	}
	
	public int getId() {
		return this.id;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public ArrayList<Ride> getRides() {
		return rides;
	}

	public void setRides(ArrayList<Ride> rides) {
		this.rides = rides;
	}

	public boolean add(Ride e, int maxStep) {
		if (e == null) {
			return true;
		}
		int newStep = this.getStep() + e.getDistance() + this.getDistanceTo(e.getStartX(), e.getStartY());
		if (newStep > maxStep) {
			return false;
		}
		this.setStep(newStep);
		this.setPosX(e.getEndX());
		this.setPosY(e.getEndY());
		return rides.add(e);
	}
	
	public int getDistanceTo(int posX, int posY) {
		int xDistance = this.getPosX() - posX;
		if (xDistance < 0) {
			xDistance *= -1;
		}
		int yDistance = this.getPosY() - posY;
		if (yDistance < 0) {
			yDistance *= -1;
		}
		return xDistance + yDistance;
	}

	public Ride getCurrentRide() {
		return currentRide;
	}

	public void setCurrentRide(Ride currentRide, int maxStep) {
		if (this.add(currentRide, maxStep)) {
			this.currentRide = currentRide;
		}		
	}

}
