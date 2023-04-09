package PUZ;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import Shared.LaazyContainers;

public class PUZ_06 {
    public static void main(String[] args){
        List<Integer> leftSide = Arrays.asList(1,3,4,6,8,9);
        System.out.println(returnToLeft(leftSide,new ArrayList<>(),null));
        System.out.println(leafs);    }
    public static int leafs = 0;
    /**
     * Pure Function
     * @param leftSide state BEFORE update.
     * @param rightSide state BEFORE update.
     * @param A Guide
     * @param B Cargo
     * @return Total cost of the BEST path from this subtree of possibilities, List of passes eg. 1,2; 1,null; etc.
     */
    private static LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>> passToRight(List<Integer> leftSide,List<Integer> rightSide,Integer A, Integer B){
        //make copies to prevent dirty operations on shared lists
        List<Integer>
                cPleftSide = new ArrayList<>(leftSide),
                cPrightSide = new ArrayList<>(rightSide);
        //update lists and set cost;
        Integer cost = Math.max(A,B);
        cPleftSide.remove(A);
        cPrightSide.add(A);

        cPleftSide.remove(B);
        cPrightSide.add(B);

        cPleftSide.sort(Comparator.naturalOrder());
        cPrightSide.sort(Comparator.naturalOrder());
        //if left side NOT empty force all possibilities of passage to right and pick best one
        LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>> result = new LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>>(Integer.MAX_VALUE,new ArrayList<LaazyContainers.C2<Integer,Integer>>());

        if(cPleftSide.size()>0){//if left side is empty then there is no point in returning there. zero the cost from max and return value (we are leaf)

            for(int a = 0; a<=cPrightSide.size()-1; a++){
                LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>> contestant = returnToLeft(cPleftSide,cPrightSide,cPrightSide.get(a));
                if(contestant.a<result.a)
                    result = contestant;
            }

        }else {
            result.a = 0;
            leafs++;
        }
        //add our own cost to the best subtree
        result.a+=cost;
        result.b.add(new LaazyContainers.C2<Integer,Integer>(A,B));
        //return
        if(result.b.contains(new LaazyContainers.C2<Integer,Integer>(null,null)))
            Math.max(1,1);
        return result;
    }

    /**
     * Pure Function
     * @param leftSide state BEFORE update.
     * @param rightSide state BEFORE update.
     * @param A Guide
     * @return Total cost of the BEST path from this subtree of possibilities, List of passes eg. 1,2; 1,null; etc.
     */
    private static LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>> returnToLeft(List<Integer> leftSide,List<Integer> rightSide,Integer A){
        //make copies to prevent dirty operations on shared lists
        List<Integer>
                cPleftSide = new ArrayList<>(leftSide),
                cPrightSide = new ArrayList<>(rightSide);
        //if this is  NOT init then update lists and set cost;
        Integer cost = 0;
        if(A!=null)
        {
            cPrightSide.remove(A);
            cPleftSide.add(A);
            cPleftSide.sort(Comparator.naturalOrder());
            cost = A;
        }
        //force all possibilities of passage to right and pick best one
        LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>> result = new LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>>(Integer.MAX_VALUE,null);

        for(int a = 0; a<=cPleftSide.size()-2; a++){
            for(int b = a+1; b<=cPleftSide.size()-1; b++){
                LaazyContainers.C2<Integer,List<LaazyContainers.C2<Integer,Integer>>> contestant = passToRight(cPleftSide,cPrightSide,cPleftSide.get(a),cPleftSide.get(b));
                if(contestant.a<result.a)
                    result = contestant;
            }
        }
        //add our own cost to the best subtree
        result.a+=cost;
        result.b.add(new LaazyContainers.C2<Integer,Integer>(A,null));
        //return
        if(result.b.contains(new LaazyContainers.C2<Integer,Integer>(null,null)))
            Math.max(1,1);
        return result;
    }
}
