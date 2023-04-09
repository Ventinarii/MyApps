package FacCalc;

public class FacNukFuelCalc {
    private static class Nukfuel{
        public static float IrPl = 10;
        public static float U235 = 1;
        public static float U238 = 19;
        //=================================
        public static float Nukfuel = 10;//*1.2f;

        public static float time = 10;
    }
    private static class NukfuelProcess{
        public static float SpendNukfuel = 5;
        //=================================
        public static float U238 = 3;//*1.2f;

        public static float time = 60;
    }
    private static class NukfuelInv{
        public static float IrPl = 0;
        public static float U235 = 0;
        public static float U238 = 0;
        public static float Nukfuel = 50*(2*6)*4*10;//725;//100000;
        public static float SpendNukfuel = 0;

        public static float FuelPower = 0;

        public static float time = 0;
        public static String toStringStatic() {
            float trainslots = (2*6)*4;
            float stackSlots = 50;
            return "NukfuelInv{" + System.lineSeparator() +
                    ", IrPl=" + IrPl + ";" + (IrPl/(stackSlots*trainslots)) + System.lineSeparator() +
                    ", U235=" + U235 + ";" + (U235/(stackSlots*trainslots)) + System.lineSeparator() +
                    ", U238=" + U238 + ";" + (U238/(stackSlots*trainslots)) + System.lineSeparator() +
                    ", Nukfuel=" + Nukfuel + System.lineSeparator() +
                    ", SpendNukfuel=" + SpendNukfuel + System.lineSeparator() +
                    ", FuelPower=" + FuelPower + System.lineSeparator() +
                    ", time=" + time + System.lineSeparator() +
                    '}';
        }
    }

    public static void main(String[] args){
        while (NukfuelInv.Nukfuel>0){
            System.out.println("Cycle: "+NukfuelInv.Nukfuel);
            //burn
            NukfuelInv.FuelPower += NukfuelInv.Nukfuel;
            NukfuelInv.SpendNukfuel += NukfuelInv.Nukfuel;

            NukfuelInv.time += NukfuelInv.Nukfuel*200/(4*4);

            NukfuelInv.Nukfuel = 0;

            //process
            while(NukfuelProcess.SpendNukfuel<=NukfuelInv.SpendNukfuel){
                NukfuelInv.SpendNukfuel -= NukfuelProcess.SpendNukfuel;
                NukfuelInv.U238 += NukfuelProcess.U238;
                //NukfuelInv.time += NukfuelProcess.time;
            }

            //produce
            while(keepprocessing()){
                NukfuelInv.IrPl -= Nukfuel.IrPl;
                NukfuelInv.U235 -= Nukfuel.U235;
                NukfuelInv.U238 -=Nukfuel.U238;
                NukfuelInv.Nukfuel += Nukfuel.Nukfuel;
                //NukfuelInv.time += Nukfuel.time;
            }
        }
        System.out.println(NukfuelInv.toStringStatic());
        System.out.println(
                        (NukfuelInv.time/60)+" "+
                        (NukfuelInv.time/(60*60))+" "+
                        (NukfuelInv.time/(60*60*24))+" "+
                        (NukfuelInv.time/(60*60*24*365))+" ");
    }

    private static boolean keepprocessing(){
        //if(NukfuelInv.IrPl<Nukfuel.IrPl)return false;
        //if(NukfuelInv.U235<Nukfuel.U235)return false;
        if(NukfuelInv.U238<Nukfuel.U238)return false;
        return true;
    }
}
