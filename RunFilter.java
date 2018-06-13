
import java.io.IOException;


public class RunFilter {

    
    public static void main(String[] args) {
		Integer k = Integer.parseInt(args[2]);
        FilterPairs p = new FilterPairs(k);
        try{
            p.readFile(args[0]);
            p.filter();
            p.printFile(args[1]);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
