import java.util.*;

public class AssociativeProcessor {
    public static final char TRUE = '1';
    public static final char FALSE = '0';

    private ArrayList<String> table;

    public AssociativeProcessor(){
        table = new ArrayList<>();
    }

    public void setTable(String value) {
        this.table.add(value);
    }

    public String searchNearest(String line, boolean flag){
        ArrayList<String> values = new ArrayList<>();
        for (String value: table) {
            boolean g = false;
            boolean l = false;
            for (int i = 0; i < value.length(); i++){
                g = g || (!logicOperation(line.charAt(i)) && logicOperation(value.charAt(i)) && !l);
                l = l || (logicOperation(line.charAt(i)) && !logicOperation(value.charAt(i)) && !g);
            }
            if (flag && !g && l) values.add(value);
            if (!flag && g && !l) values.add(value);
        }
        return desiredValue(values, flag);
    }

    public boolean logicOperation(char number){
        return number == TRUE;
    }

    public String desiredValue(ArrayList<String> values, boolean flag){
        int index = 0;
        ArrayList<Integer> intValues = new ArrayList<>();
        for (int i = 0; i < values.size(); i++){
            while (values.get(i).charAt(index) == FALSE){
                index++;
            }
            intValues.add(Integer.parseInt(values.get(i).substring(index)));
            index = 0;
        }
        int i = 0;
        for (int value: intValues){
            if (flag && Collections.max(intValues) == value) return values.get(i);
            if (!flag && Collections.min(intValues) == value) return values.get(i);
            i++;
        }
        return "";
    }

    public List<String> rangeSearch(String leftRange, String rightRange){
        ArrayList<String> values = new ArrayList<>();
        ArrayList<Boolean> flags = new ArrayList<>();
        for (int i = 0; i < table.size(); i++){
            flags.add(true);
        }
        int index = 0;
        for (String value: table) {
            boolean g = false;
            boolean l = false;
            for (int i = 0; i < value.length(); i++){
                g = g || (!logicOperation(leftRange.charAt(i)) && logicOperation(value.charAt(i)) && !l);
                l = l || (logicOperation(leftRange.charAt(i)) && !logicOperation(value.charAt(i)) && !g);
            }
            if (!g && l) flags.set(index, false);
            index++;
        }
        int identificator = 0;
        for (String value: table) {
            boolean g = false;
            boolean l = false;
            for (int i = 0; i < value.length(); i++){
                g = g || (!logicOperation(rightRange.charAt(i)) && logicOperation(value.charAt(i)) && !l);
                l = l || (logicOperation(rightRange.charAt(i)) && !logicOperation(value.charAt(i)) && !g);
            }
            if (g && !l) flags.set(identificator, false);
            identificator++;
        }
        for (int i = 0; i < flags.size(); i++){
            if (flags.get(i)) values.add(table.get(i));
        }
        showRange(values);
        return values;
    }

    @Override
    public String toString() {
        return "AssociativeProcessor{" +
                "table=" + table +
                '}';
    }
    public void show(){
        System.out.println(table);
    }
    public void showRange(ArrayList<String> values){
        System.out.print("[");
        int index = 0;
        for (String value: values) {
            System.out.print(value);
            if (values.size() - index != 1) System.out.print(", ");
            index++;
        }
        System.out.println("]");
    }
}
