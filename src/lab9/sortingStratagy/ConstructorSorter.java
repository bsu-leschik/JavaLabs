package lab9.sortingStratagy;

import lab9.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConstructorSorter implements Sorter{
    @Override
    public TreeSet<Student> sortData(ArrayList<Student> students, int course) {
            if (students.isEmpty()){
                throw new IllegalArgumentException("Students is empty");
            }

            TreeSet<Student> studentsSorted = new TreeSet<>(new Comparator<Student>() {
                @Override
                public int compare(Student student, Student t1) {
                    if (student.getSurname().compareToIgnoreCase(t1.getSurname()) == 0){
                        return student.getGroupNumber() - t1.getGroupNumber();
                    }
                    return student.getSurname().compareToIgnoreCase(t1.getSurname());
                }
            });

            studentsSorted.addAll(students.stream().filter(new Predicate<Student>() {
                @Override
                public boolean test(Student student) {
                    return student.getCourseNumber() == course;
                }
            }).collect(Collectors.toCollection(ArrayList::new)));

            return studentsSorted;
        }
    }
