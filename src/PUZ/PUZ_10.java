package PUZ;

import java.util.ArrayList;

public class PUZ_10 {
    public static void main (String[] args){
        for(int z = 1; z<=1000; z++){
            ArrayList<Integer> react = new ArrayList<>();
            for(int i = 1; i<=z; i++)
                if(z%i==0)
                    react.add(i);

            if(react.size()%2==1)System.out.println("Z:"+z+" C:"+react.size()+" reac:"+react);
            //if(react.size()%2==1)System.out.print(z+";");
        }
    }
}
