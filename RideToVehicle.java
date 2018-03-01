
public class RideToVehicle implements Comparable<RideToVehicle>{

	private Ride ride;
	private Vehicle vehicle;
	private int startTime;
	
	public RideToVehicle(Ride ride, Vehicle vehicle, int startTime) {
		this.ride = ride;
		this.vehicle = vehicle;
		this.startTime = startTime;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	@Override
	public int compareTo(RideToVehicle o) {
		return o.getStartTime() - this.getStartTime();
	}
	
}
