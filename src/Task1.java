import java.lang.Comparable;
import java.util.Scanner;

public class Task1 {

    // Создаём класс Student, который реализует метод интерфейса Comparable
    public static class Student implements Comparable<Student> {
        // метод интерфейса Comparable сравнивает объекты, соответственно создаем класс-оболочку Integer
        private Integer IDNumber;

        public Student() {
            int IDNumber;
        }

        public Integer getIDNumber() {
            return IDNumber;
        }

        public void setIDNumber(Integer IDNumber) {
            this.IDNumber = IDNumber;
        }

        // переопределяем метод интерфейса Comparable, для сравнения ID Number
        @Override
        public int compareTo(Student student) {
            return this.IDNumber.compareTo(student.IDNumber);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "IDNumber=" + IDNumber +
                    '}';
        }
    }

    /* Сортировка вставками - разделение массива на отсортированную и неотсортированную части,
    при этом на каждом шаге из неотсортированной части выбирается элемент и вставляется в правильное место
    в отсортированной части */
     private static void InsertionSort(Student[] students) {
         for (int i = 1; i < students.length; i++) {
             // Вытаскиваем значение элемента
             Student current = students[i];
             // Перемещаемся по элементам, которые стоят перед вытащенным элементом
             int j = i - 1;
             // Если вытащили значение меньшее — передвигаем больший элемент дальше
             while (j >= 0 && students[j].compareTo(current) > 0) {
                 students[j + 1] = students[j];
                 j--;
             }
             // В освободившееся место вставляем вытащенное значение
             students[j + 1] = current;
         }
     }

    public static void main(String[] args) {
        System.out.println("Enter the number of students: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt(); // количество студентов
        Student[] students = new Student[number]; // массив с ID Number студентов
        for (int i = 0; i < number; i++) {
            students[i] = new Student();
            System.out.println("Enter student's ID Number: ");
            int ID_Number = sc.nextInt();
            students[i].setIDNumber(ID_Number); // устанавливаем значение ID Number для каждого из студентов
        }
        System.out.println("The original array: ");
        for (Student s: students) System.out.println(s);
        InsertionSort(students); // применяем сортировку вставками
        System.out.println("Sorted array: ");
        for (Student s: students) System.out.println(s);
    }
}