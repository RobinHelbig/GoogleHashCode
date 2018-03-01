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
           Ride ride = new Ride(i - 1, r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5));
           rides.add(ride);
       }
       ArrayList<Vehicle> vehicles = new ArrayList<>();
       for (int i = 0; i < parameters.get(2); i++) {
           Vehicle vehicle = new Vehicle();
           boolean found = true;
           while(vehicle.getStep() <= parameters.get(5) && found && vehicle.getRides().size()<9999) {
               found = false;
               rides.sort(new Comparator<Ride>() {
                   @Override
                    public int compare(Ride o1, Ride o2) {
                        return vehicle.getDistanceTo(o1.getStartX(), o1.getStartY()) - vehicle.getDistanceTo(o2.getStartX(), o2.getStartY());
                    }
               });
               for(int j=0; j<rides.size(); j++){
                    if(vehicle.add(rides.get(j), parameters.get(5))) {
                      rides.remove(j);
                      found = true;
                      break;
                   } 
               }
           }   
           
           ArrayList<Integer> carRideIds = new ArrayList<Integer>();
           ArrayList<Ride> carRides = vehicle.getRides();
           carRideIds.add(carRides.size());
           for(int j = 0;j<carRides.size();j++){
               carRideIds.add(carRides.get(j).getId());
            }
            output.add(carRideIds);
       }
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

