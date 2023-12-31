/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С., Кулешов И.В
// Реализация прямого нечёткого вывода, используя импликацию Лукасевича
// 09.10.2023

//Класс матрицы
public class Matrix {
    //Первая переменная
    private String x1;
    //Вторая переменная
    private String x2;
    //Значение
    private Float number;

    public Matrix(String x1, String x2, Float number) {
        this.x1 = x1;
        this.x2 = x2;
        this.number = number;
    }

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "<" +
                "<" + x1 + ", " +
                x2 + ">, " + String.format("%.1f", number) +
                "> ";
    }
}
