
public class KwotaZlotych {

    static String[] wordsX__ = {"","sto ","dwieście ","trzysta ","czterysta ","pięćset ","sześćset ","siedemset ","osiemset ","dziewięćset "};
    static String[] words_X_ = {"","dziesięć ","dwadzieścia ","trzydzieści ","czterdzieści ","pięćdziesiąt ","sześćdziesiąt ","siedemdziesiąt ","osiemdziesiąt ","dziewięćdziesiąt "};
    static String[] words_1X = {"","jedenaście ","dwanaście ","trzynaście ","czternaście ","piętnaście ","szesnaście ","siedemnaście ","osiemnaście ","dziewiętnaście "};
    static String[] words__X = {"","jeden ","dwa ","trzy ","cztery ","pięć ","sześć ","siedem ","osiem ","dziewięć "};
    static String[] wordsKilo = {"tysiąc ","tysiące ","tysięcy "};
    static String zero = "zero ";
    public static void main (String[] args){
        var money = 1000.0;
        var str = "";

        //split=========================================================================================================
        var moneyX__ = Math.floor(money/1000);
        money -= moneyX__*1000;
        var money_X_ = Math.floor(money);
        money -= money_X_;
        var money__X = Math.floor(money*100);

        if(moneyX__==0){
            //do nothing
        }
        else if(moneyX__==1){
            str+=wordsKilo[0];
        }
        else if(moneyX__==2){
            str+=words__X[1]+wordsKilo[1];
        }
        else{
            str+=GetString(moneyX__)+wordsKilo[2];
        }
        str+=GetString(money_X_);
        str += "zł ";
        var tmp =GetString(money__X);
        if(tmp.isEmpty())
            tmp = zero;
        str += tmp;
        str += "gr";
        System.out.println(str);
    }
    public static String GetString(double money){
        int moneyX__ = (int)Math.floor(money/100);
        money -= moneyX__*100;
        int money_X_ = (int)Math.floor(money/10);
        money -= money_X_*10;
        int money__X = (int)Math.floor(money);
        var str = "";
        str+=wordsX__[moneyX__]+words_X_[money_X_]+words__X[money__X];
        return str;
    }
}
