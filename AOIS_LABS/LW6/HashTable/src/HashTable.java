import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HashTable {
    public static final int SIZE = 20;
    private ArrayList<Cell> cells;
    private ArrayList<Integer> indexes = new ArrayList<>();
    private static final int DELETE_INDEX = -1;
    private static final int HALF_SIZE = SIZE / 2;
    public static final String[] TERMS = {"Биология", "Фотосинтез", "Бактерия", "Ареал", "Животные", "Растения",
            "Эволюция", "Грибы", "Протисты", "Хищники"};
    public static final String[] DEFINITION = {"Наука о живой природе, о закономерностях органической жизни",
            "Процесс синтеза органических веществ из неорганических за счет энергии света",
            "Микроорганизм, вид микроба",
            "Область распространения, расселения кого-чего-н",
            "Живое существо, обладающее способностью двигаться и чувствовать",
            "Организм, обычно развивающийся в неподвижном состоянии и питающийся неорганическими и органическими веществами почвы и воздуха",
            "Развитие, процесс постепенного непрерывного количественного изменения кого-чего-н., подготавливающий качественные изменения",
            "Низшее растение, не образующее цветков и семян и размножающееся спорами",
            "Парафилетическая группа, к которой относят все эукариотические организмы, не входящие в состав животных, грибов и растений",
            "Хищное животное"};
    public class Cell{
        private String key;
        private int id;
        private String value;
        private boolean state;

        public void setId(int id) {
            this.id = id;
        }

        public Cell(String key, int id, String value, boolean state) {
            this.key = key;
            this.id = id;
            this.value = value;
            this.state = state;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "key='" + key + '\'' +
                    ", id=" + id +
                    ", value='" + value + '\'' +
                    ", state=" + state +
                    '}';
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public HashTable(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public ArrayList<Cell> createHashTable(){
        int index = 0;
        while (index < SIZE){
            cells.add(new Cell("", 0, "", false));
            index++;
        }
        for (int i = 0; i < HALF_SIZE; i++) {
            String key = TERMS[i];
            String value = DEFINITION[i];
            int id = getIndex(key);
            Cell cell = new Cell(key, id, value, true);
            cell.setId(collision(cell));
            indexes.add(cell.id);
            cells.set(cell.id, cell);
        }
        return cells;
    }

    public int getIndex(String key){
        int id = 0;
        for (int j = 0; j < key.length(); j++) id += key.charAt(j);
        return id % SIZE;
    }

    public int collision(Cell cell){
        for (int i = 0; i < indexes.size(); i++){
            if (cell.id == cells.get(indexes.get(i)).id){
                if (cells.size() - cell.id == 1) cells.add(new Cell("", 0, "", false));
                for (int j = cell.id + 1; j < cells.size(); j++){
                    if (!cells.get(j).state || (cells.get(j).state && cells.get(j).id == DELETE_INDEX)){
                        return j;
                    }
                }
            }
        }
        return cell.id;
    }

    public ArrayList<Cell> addValue(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название термина:");
        String key = scanner.nextLine();
        System.out.println("Введите определение:");
        String value = scanner.nextLine();
        int id = getIndex(key);
        Cell cell = new Cell(key, id, value, true);
        while (id > cells.size()) cells.add(new Cell("", 0, "", false));
        cell.setId(collision(cell));
        indexes.add(cell.id);
        cells.set(cell.id, cell);
        return cells;
    }

    public ArrayList<Cell> deleteValue(String key){
        int id = getIndex(key);
        for (int i = id; i < cells.size(); i++){
            if (!cells.get(i).state){
                System.out.println("Такой ячейки нет");
                return cells;
            }
            if (key.equals(cells.get(i).key)){
                cells.set(id, new Cell("", DELETE_INDEX, "", true));
                return cells;
            }
        }
        System.out.println("Неправильный ключ");
        return cells;
    }

    public void getInfo(String key){
        int id = getIndex(key);
        boolean flag = false;
        for (int i = id; i < cells.size(); i++){
            if (!cells.get(i).state){
                if (!flag) System.out.println("Такой ячейки нет");
                return;
            }
            if (key.equals(cells.get(i).key)){
                System.out.println(cells.get(i));
                flag = true;
            }
        }
    }

    public void showTable(){
        for (int i = 0; i < this.cells.size(); i++) System.out.println(String.valueOf(i)+". "+this.cells.get(i));
    }
}
