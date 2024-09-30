import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Task2 {

    // Создаём класс Student для характеристики студентов
    public static class Student {
        private String name; // имя
        private Double GPA; // средний балл

        public Student(String name, Double GPA) {
            this.name = name;
            this.GPA = GPA;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getGPA() {
            return GPA;
        }

        public void setGPA(Double GPA) {
            this.GPA = GPA;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", GPA=" + GPA +
                    '}';
        }
    }

    // Создаём класс, который реализует метод интерфейса Comparator
    public static class SortingStudentsByGPA implements Comparator<Student> {
        @Override
        public int compare(Student student1, Student student2) {
            return student1.getGPA().compareTo(student2.getGPA()); // сравниваем средние баллы
        }
    }

    /* Быстрая сортировка - 1. разделение списка на две части -
    элементы, которые меньше или равны pivot и больше pivot,
    2. рекурсивная сортировка этих части до тех пор, пока список не будет отсортирован.*/
    public static class QuickSort {

        // Конкретно метод для сортировки списка
        public static void sort(List<Student> students, Comparator<Student> comparator) {
            quickSort(students, 0, students.size() - 1, comparator);
        }

        // Рекурсивный метод сортировки студентов (low - нижняя, high - верхняя граница списка)
        private static void quickSort(List<Student> students, int low, int high, Comparator<Student> comparator) {
            if (low < high) { // продолжаем сортировку
                // вызываем метод partition для разделения списка на две части
                int pivotIndex = partition(students, low, high, comparator);
                // Рекурсивно вызываем метод quickSort для сортировки двух частей списка
                quickSort(students, low, pivotIndex - 1, comparator);
                quickSort(students, pivotIndex + 1, high, comparator);
            }
        }

        // Метод для определения опорного элемента и разделения массива на две части
        private static int partition(List<Student> students, int low, int high, Comparator<Student> comparator) {
            // выбираем последний элемент списка в качестве pivot (опорного элемента).
            Student pivot = students.get(high);
            int i = low - 1;
            // сравниваем каждый элемент с pivot
            for (int j = low; j < high; j++) {
                // учитываем сравнение по среднему баллу
                if (comparator.compare(students.get(j), pivot) <= 0) {
                    i++;
                    swap(students, i, j); // меняем местами
                }
            }
            swap(students, i + 1, high); // окончательная позиция pivot
            return i + 1;
        }

        // Вспомогательный метод, который меняет местами два элемента в списке
        private static void swap(List<Student> students, int i, int j) {
            Student temp = students.get(i); // метод get возвращает элемент по указанному индексу
            students.set(i, students.get(j));
            students.set(j, temp);
        }
    }


    public static void main(String[] args) {
        System.out.println("Enter the number of students: ");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        List<Student> students = new ArrayList<>(number); // создаём список студентов
        for (int i = 0; i < number; i++) {
            System.out.println("Enter student's name: ");
            String name = sc.next();
            System.out.println("Enter student's GPA: ");
            double GPA = sc.nextDouble();
            Student s = new Student(name, GPA); // для элемента списка создаем экземпляр класса Student
            students.add(s); // добавляем элемент в список
        }

        System.out.println("The original list: ");
        for (Student s: students) System.out.println(s);

        SortingStudentsByGPA comparator = new SortingStudentsByGPA(); // создаём экземпляр предмета сравнения
        QuickSort.sort(students, comparator); // сортируем
        System.out.println("Sorted list: ");
        for (Student s: students) System.out.println(s);
    }
}
