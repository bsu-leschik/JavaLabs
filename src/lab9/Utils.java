package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

    public static ArrayList<Student> readFromFile(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        reader.useDelimiter("\n");

        ArrayList<Student> students = new ArrayList<>();

        while (reader.hasNext()){
            students.add(parseStudent(reader.next()));
        }

        return students;
    }

    public static TreeSet<Student> sortData(ArrayList<Student> students, int course) throws IllegalArgumentException {
        if (students.isEmpty()){
            throw new IllegalArgumentException("Students is empty");
        }

        TreeSet<Student> studentsSorted = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                if (student.surname.compareToIgnoreCase(t1.surname) == 0){
                    return student.groupNumber - t1.groupNumber;
                }
                return student.surname.compareToIgnoreCase(t1.surname);
            }
        });

        studentsSorted.addAll(students.stream().filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.courseNumber == course;
            }
        }).collect(Collectors.toCollection(ArrayList::new)));

        return studentsSorted;
    }

    public static Student parseStudent(String data) throws InputMismatchException{
        Scanner lineScanner = new Scanner(data);
        lineScanner.useDelimiter(" ");
        return new Student(lineScanner.nextInt(),
                lineScanner.next(),
                lineScanner.nextInt(),
                lineScanner.nextInt());
    }
}