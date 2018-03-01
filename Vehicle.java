import java.util.ArrayList;

public class Vehicle {
	
	private int posX;
	private int posY;
	
	private int step;
	
	private ArrayList<Ride> rides;
	
	public Vehicle() {
		this.posX = 0;
		this.posY = 0;
		this.step = 0;
		this.rides = new ArrayList<>();
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
		int newStep = this.getStep() + e.getDistance() + this.getDistanceTo(e.getStartX(), e.getStartY());
		if (newStep > maxStep) {
			return false;
		}
		System.out.println(newStep);
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

}
