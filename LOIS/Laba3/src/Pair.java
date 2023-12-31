/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С., Кулешов И.В
// Реализация прямого нечёткого вывода, используя импликацию Лукасевича
// 09.10.2023

//Описание пары
public class Pair {
    //Переменная
    private String name;
    //Значение
    private float number;
    public Pair(String name, float number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public float getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "<" +
                name + ", " +
                String.format("%.1f", number) +
                ">";
    }
}