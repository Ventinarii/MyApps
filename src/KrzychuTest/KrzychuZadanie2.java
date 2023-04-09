package KrzychuTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class KrzychuZadanie2 {
    public static void main (String[] args){
        //System.out.println(Calc(123456789));
        //System.out.println(Calc(2147483647));
        System.out.println(Calc(100000000));
    }
    public static int maxRepetitions=0;

    public static int Calc(int n){
        long start = System.currentTimeMillis();
        long last = start;

        boolean[] arr = new boolean[n+1];
        ArrayList<Integer> primes = new ArrayList<>();
        arr[0] = true;
        arr[1] = true;
        arr[2] = false;

        System.out.println("time to init array:"+(System.currentTimeMillis()-last));
        last = System.currentTimeMillis();

        for(int i = 3; i<=n; i+=2){
            if(!arr[i]) {
                primes.add(i);
                for (int wks = i * 2; wks <= n; wks += i)
                    arr[wks] = true;
            }
        }

        System.out.println("time to get primes:"+(System.currentTimeMillis()-last));
        last = System.currentTimeMillis();

        List<String> hashes = primes
                .parallelStream()
                .map(i->{
                    char[] chars = i.toString().toCharArray();
                    Arrays.sort(chars);
                    String str = String.valueOf(chars);
                    return str;
                }).collect(Collectors.toList());

        System.out.println("time to create hashes:"+(System.currentTimeMillis()-last));
        last = System.currentTimeMillis();

        HashMap <String,Integer> repetitions = new HashMap <>();
        hashes.forEach(i->{
                repetitions.putIfAbsent(i,0);

                int tmp = repetitions.get(i)+1;
                repetitions.put(i,tmp);
                if(maxRepetitions<tmp)
                    maxRepetitions = tmp;
            });

        System.out.println("time to get repetition list:"+(System.currentTimeMillis()-last));
        last = System.currentTimeMillis();

        System.out.println("total time:"+(System.currentTimeMillis()-start));

        return maxRepetitions;
    }

    public static int Calc2(int n){
        long start = System.currentTimeMillis();

        boolean[] arr = new boolean[n+1];
        ArrayList<Integer> primes = new ArrayList<>();

        arr[0] = true;
        arr[1] = true;

        for(int i = 2; i<=n; i++){
            if(!arr[i]) {
                primes.add(i);
                for (int wks = i * 2; wks <= n; wks += i)
                    arr[wks] = true;
            }
        }

        System.out.println("time:"+(System.currentTimeMillis()-start));

        HashMap<String,Integer> repetitions = new HashMap<>();

        primes
                .stream()
                .map(i->{
                    char[] chars = i.toString().toCharArray();
                    Arrays.sort(chars);
                    String str = String.valueOf(chars);
                    return str;
                })
                .forEach(i->{
                    repetitions.putIfAbsent(i,0);

                    int tmp = repetitions.get(i)+1;
                    repetitions.put(i,tmp);
                    if(maxRepetitions<tmp)
                        maxRepetitions = tmp;
                });

        System.out.println("time:"+(System.currentTimeMillis()-start));

        return maxRepetitions;
    }
}
