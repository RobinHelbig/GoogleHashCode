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
       System.out.println(parameters);
       ArrayList<Ride> rides = new ArrayList<>();
       for (int i = 1; i < data.size(); i++) {
    	   ArrayList<Integer> r = data.get(i);
    	   Ride ride = new Ride(i -1, r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5));
    	   rides.add(ride);
       }
       System.out.println(rides);
       ArrayList<Vehicle> vehicles = new ArrayList<>();
       for (int i = 0; i < parameters.get(2); i++) {
    	   Vehicle vehicle = new Vehicle();
    	   ArrayList<Integer> out = new ArrayList<>();
    	   System.out.println("Fahrzeug " + i);
    	   while(vehicle.getStep() <= parameters.get(5) && rides.size() > 0) {
    		   rides.sort(new Comparator<Ride>() {
        		   @Override
        			public int compare(Ride o1, Ride o2) {
        				return vehicle.getDistanceTo(o1.getStartX(), o1.getStartY()) - vehicle.getDistanceTo(o2.getStartX(), o2.getStartY());
        			}
        	   });
    		   if(vehicle.add(rides.get(0), parameters.get(5))) {
    			   out.add(rides.get(0).getId());
        		   int length = rides.size();
        		   rides.remove(0);
    		   } else {
    			   break;
    		   }
    		   
    		   
    	   }   
    	   System.out.println(rides.size());
    	   vehicles.add(vehicle);    	   
    	  
    		  ArrayList<Integer> help = new ArrayList<>();
    		  help.add(out.size());
    		  help.addAll(out);
    		  output.add(help);
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

