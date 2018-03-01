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
       for (int i = 0; i < parameters.get(2) && i <= parameters.get(3); i++) {
    	   ArrayList<Integer> ride = new ArrayList<>();
    	   ArrayList<Integer> abc = data.get(i + 1);
    	   ride.add(1);
    	   ride.add(i);
    	   output.add(ride);
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

