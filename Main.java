import java.util.*;
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
		rides.sort(new Comparator<Ride>() {

			@Override
			public int compare(Ride o1, Ride o2) {
				return o1.getStartTime() - o2.getStartTime();
			}
		});
		
		int maxSteps = parameters.get(5);
		int problem = 0;
		for(int i = 0; i <= maxSteps; i++) {
			ArrayList<Vehicle> freeVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getCurrentRide() != null) {
					if (vehicle.getCurrentRide().getRealEndTime() == i) {
						vehicle.setCurrentRide(null, maxSteps);
						freeVehicles.add(vehicle);
					}
				} else {
					freeVehicles.add(vehicle);
				}
			}
			ArrayList<Ride> remove = new ArrayList<Ride>();
			for (Ride ride : rides) {
				Vehicle vehicle = this.getNextFreeVehicle(freeVehicles, ride);
				if (vehicle == null) {
					continue;
				}
				int distance = vehicle.getDistanceTo(ride.getStartX(), ride.getStartY());
				int time = distance + i + ride.getDistance();
				System.out.println(time + " "+ ride.getEndTime());
				ArrayList<Ride> in = this.getRidesInTime(rides, i, time);
				for (Ride r : in) {
					if (vehicle.getDistanceTo(r.getStartX(), r.getStartY()) < distance) {
						continue;
					}
				}
				if (time > ride.getEndTime()) {
					problem++;
					remove.add(ride);
				} else {
					vehicle.setCurrentRide(ride, maxSteps);
					System.out.println("zuweisung");
					remove.add(ride);
				}
			}
			System.out.println("Step " + i);
			rides.removeAll(remove);
		}
		
		System.out.println("missing " + problem + " " + data.size());
		int points = 0;
		for (Vehicle vehicle : vehicles) {
			ArrayList<Integer> out = new ArrayList<>();
			out.add(vehicle.getRides().size());
			for (Ride ride : vehicle.getRides()) {
				points += ride.getDistance();
				out.add(ride.getId());
				if (ride.getRealStartTime() == ride.getStartTime()) {
					points += parameters.get(4);
				}
			}
			output.add(out);
		}
		System.out.println(points + " " + output);
		IO.save(output);
	}
	
	public Vehicle getNextFreeVehicle(ArrayList<Vehicle> vehicles, Ride ride) {
		vehicles.sort(new Comparator<Vehicle>() {

			@Override
			public int compare(Vehicle o1, Vehicle o2) {
				return o1.getDistanceTo(ride.getStartX(), ride.getStartY()) - o2.getDistanceTo(ride.getStartX(), ride.getStartY());
			}
		});
		if (vehicles.size() == 0) {
			return null;
		}
		return vehicles.get(0);
	}
	
	public ArrayList<Ride> getRidesInTime(ArrayList<Ride>rides, int startTime, int endTime) {
		ArrayList<Ride> r = new ArrayList<>();
		for (Ride ride : rides) {
			if (
					(ride.getStartTime() >= startTime && ride.getStartTime() <= endTime)
					|| (ride.getEndTime() >= startTime && ride.getEndTime() <= endTime)
					|| (ride.getStartTime() <= startTime && ride.getEndTime() >= endTime)
					) {
				r.add(ride);
			}
		}
		return r;
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

