import java.util.*;
import java.util.ArrayList;
import java.io.*;
public class Main
{   
    public Main()
    {
       ArrayList<ArrayList<Integer>> data = IO.load();
       for(int i = 0; i< data.size(); i++){
           data.set(i,sort(data.get(i)));
        }
       IO.save(data);
    }
    
    public static void main(String[] args) {
    	Main main = new Main();
    	System.out.println("Hallo");
    	while(true) {
    		
    	}
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

