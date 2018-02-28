import java.io.*;
import java.util.ArrayList;
public class IO
{
    public static ArrayList<ArrayList<Integer>> load(){
        ArrayList<ArrayList<Integer>> data = new ArrayList();
        File f = new File("./input.txt");
        try{
            FileReader filereader = new FileReader(f); 
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String zeile;
            while ((zeile = bufferedreader.readLine())!=null) {
                String[] daten = zeile.split(" ");
                ArrayList<Integer> rowData = new ArrayList<Integer>();
                for(int i = 0; i<daten.length; i++){
                    rowData.add(Integer.parseInt(daten[i]));
                }
                data.add(rowData);
            }
            bufferedreader.close();
            return data;
            } catch (IOException e) {
              e.printStackTrace();
        }
        return null;
    }
    
    public static void save(ArrayList<ArrayList<Integer>> data){
        File f = new File("./output.txt");
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i< data.size(); i++){
                ArrayList<Integer> rowData = data.get(i);
                String row = "";
                for(int j = 0; j< rowData.size(); j++){
                    int x = rowData.get(j);
                    row = row + x;
                    if(j<rowData.size()-1)row = row + " ";
                }
                bw.write(row);
                bw.newLine();
            }               
            bw.close();  
        } catch ( IOException e ) { 
            e.printStackTrace();
        }
    }
}
