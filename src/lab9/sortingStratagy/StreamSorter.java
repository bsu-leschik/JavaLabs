package lab9.sortingStratagy;

import lab9.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;

public class StreamSorter implements Sorter{
    @Override
    public TreeSet<Student> sortData(ArrayList<Student> students, int course) {
        if (students.isEmpty()){
            throw new IllegalArgumentException("Students is empty");
        }

        TreeSet<Student> studentsSorted = new TreeSet<>();

        studentsSorted.addAll(students.stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student student, Student student1) {
                if (student.getSurname().compareToIgnoreCase(student1.getSurname()) == 0){
                    return student.getGroupNumber() - student1.getGroupNumber();
                }
                return student.getSurname().compareToIgnoreCase(student1.getSurname());
            }
        }).toList());
        studentsSorted.stream().filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getCourseNumber() == course;
            }
        });

        return studentsSorted;
    }
}
