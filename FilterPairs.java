
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class FilterPairs {
    
    private HashMap<String, HashSet<String>> userAsKey;
    private HashMap<String, HashSet<String>> businessAsKey;
    
    private int K;

    public FilterPairs(int K) {
        this.K = K;
        userAsKey = new HashMap<String, HashSet<String>>();
        businessAsKey = new HashMap<String, HashSet<String>>();
    }
    
    public void readFile(String fname) throws  IOException{
        BufferedReader in = new BufferedReader(new FileReader(fname));
        String line;
        while((line = in.readLine()) != null){
            String [] pair = line.split(" ");

            
            HashSet<String> set = new HashSet<String>();
            
            if(userAsKey.containsKey(pair[0])){
                set = userAsKey.get(pair[0]);
                set.add(pair[1]);
            }else{
                set.add(pair[1]);
            }
            userAsKey.put(pair[0], set);
            
            set = new HashSet<String>();
            if(!businessAsKey.containsKey(pair[1])){
                set.add(pair[0]);
                
            }else{
                set = businessAsKey.get(pair[1]);
                set.add(pair[0]);
            }
            
            businessAsKey.put(pair[1], set);
        } 
    }
    
    
    public void filter(){
        Iterator<String> it = userAsKey.keySet().iterator();
        while (it.hasNext()){
            HashSet<String> busin = userAsKey.get(it.next());
            if(busin.size()<K){
                it.remove();
            }
        }
        
        it = businessAsKey.keySet().iterator();
        while(it.hasNext()){
            HashSet<String> user = businessAsKey.get(it.next());
            if(user.size()<K){
                it.remove();
            }
        }
    }
    
    
    public void printFile(String fName) throws IOException{
        
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(fName));
        
        Iterator<String> it = userAsKey.keySet().iterator();
        while (it.hasNext()){
            String nextId = it.next();
            HashSet<String> busin = userAsKey.get(nextId);
            printWriter.write("User: "+nextId+" Businesses: "+busin.toString()+"\n");
        }
        
        
        printWriter.write("\n");
        it = businessAsKey.keySet().iterator();
        while (it.hasNext()){
            String nextId = it.next();
            HashSet<String> user = businessAsKey.get(nextId);
            printWriter.write("Business: "+nextId+" Users: "+user.toString()+"\n");
        }

        printWriter.close();
  
    }  
}
