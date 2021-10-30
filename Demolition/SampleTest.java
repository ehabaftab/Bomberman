
import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import processing.data.JSONObject;
import java.lang.Object;
public class SampleTest{
    public static void main(String[] args){
        StringBuilder s = new StringBuilder();
        try{

            File jsonFile = new File("config.json");
            Scanner jsonReader = new Scanner(jsonFile);
            int counter = 0;
            while(jsonReader.hasNextLine()) {
                s.append(jsonReader.nextLine());
                
            }
            System.out.println(s.toString());
            JSONObject.parse(s.toString());
            jsonReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}
