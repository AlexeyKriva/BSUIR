/////////////////////////////////
// Лабораторная работа №1 по дисциплине ЛОИС
// Выполнена студентами группы 121702 БГУИР Кривецкий А.Э., Костюков В.С., Кулешов И.В
// Реализация прямого нечёткого вывода, используя импликацию Лукасевича
// 09.10.2023

//Описывание следствия
public class Consequence {
    //Посылка
    private String nameA;
    //Вывод
    private String nameB;

    public Consequence(String nameA, String nameB) {
        this.nameA = nameA;
        this.nameB = nameB;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }
}