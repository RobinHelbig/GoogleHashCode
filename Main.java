import java.util.*;
import java.util.ArrayList;
import java.io.*;
public class Main
{   
	public Main()
	{
		ArrayList<ArrayList<Integer>> data = IO.load();
		ArrayList<ArrayList<Integer>> output = new ArrayList<>();
		ArrayList<Integer> parameters = data.get(0);
		ArrayList<Ride> rides = new ArrayList<>();
		for (int i = 1; i < data.size(); i++) {
			ArrayList<Integer> r = data.get(i);
			Ride ride = new Ride(i -1, r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5));
			rides.add(ride);
		}
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		for (int i = 0; i < parameters.get(2); i++) {
			Vehicle vehicle = new Vehicle(i);
			vehicles.add(vehicle);
		}
		for (int i = 0; i <= parameters.get(5); i++) {
			ArrayList<Vehicle> leftVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getCurrentRide() == null) {
					leftVehicles.add(vehicle);
				} else if(vehicle.getCurrentRide().getRealEndTime() >= i) {
					vehicle.setCurrentRide(null, parameters.get(5));
				}
			}
			if (rides.size() == 0) {
				break;
			}
			RideToVehicle[] mustRides = new RideToVehicle[rides.size()];
			for(int v = 0; v < leftVehicles.size(); v++) {
				Vehicle vehicle = leftVehicles.get(v);
				for (int r = 0; r < rides.size(); r++) {
					Ride ride = rides.get(r);
					int minStart = i + vehicle.getDistanceTo(ride.getStartX(), ride.getStartY());
					if (minStart == ride.getEndTime() - ride.getDistance()) {
						RideToVehicle rtv = new RideToVehicle(ride, vehicle, minStart);
						if (mustRides[r] == null) {
							mustRides[r] = rtv;
						} else if (mustRides[r].compareTo(rtv) < 0) {
							mustRides[r] = rtv;
						}
					}
				}
			}
			for (RideToVehicle rtv : mustRides) {
				if (rtv != null) {
					rtv.getVehicle().setCurrentRide(rtv.getRide(), parameters.get(5));
					leftVehicles.remove(rtv.getVehicle());
					rides.remove(rtv.getRide());
				}
				
			}
			for(Vehicle vehicle : leftVehicles) {
				rides.sort(new Comparator<Ride>() {

					@Override
					public int compare(Ride o1, Ride o2) {
						return vehicle.getDistanceTo(o1.getStartX(), o1.getStartY()) - vehicle.getDistanceTo(o2.getStartX(), o2.getStartY());
					}
				});
				if (rides.size() > 0 && rides.get(0) != null) {
					Ride ride = rides.get(0);
					int distance = vehicle.getDistanceTo(ride.getStartX(), ride.getStartY()) + ride.getDistance();
					if (distance + i <= parameters.get(5)) {
						vehicle.setCurrentRide(rides.get(0), parameters.get(5));
						rides.remove(ride);
					}    				   
				}
			}
		}
		for (Vehicle vehicle : vehicles) {
			ArrayList<Integer> out = new ArrayList<>();
			System.out.println(vehicle.getRides());
			out.add(vehicle.getRides().size());
			for (Ride ride : vehicle.getRides()) {
				out.add(ride.getId());
			}
			output.add(out);
		}
		System.out.println(output);
		IO.save(output);
	}

	public static void main(String[] args) {
		Main main = new Main();
		System.out.println("Hallo");
		while(main.getTimer()) {
			System.out.println("Test");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean getTimer() {
		return false;
	}

	public ArrayList<Integer> sort(ArrayList<Integer> array){
		for(int i = 0;i<array.size()-1;i++){
			for(int j = i+1; j<array.size();j++){
				if(array.get(j)<array.get(i)){
					int dummy = array.get(i);
					array.set(i,array.get(j));
					array.set(j,dummy);
				}
			}
		}
		return array;
	}
}

class DistanceComparator implements Comparator<Ride> {
	private Vehicle vehicle;

	public DistanceComparator(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	@Override
	public int compare(Ride o1, Ride o2) {
		return this.vehicle.getDistanceTo(o1.getStartX(), o1.getStartY()) - this.vehicle.getDistanceTo(o2.getStartX(), o2.getStartY());
	}
}

