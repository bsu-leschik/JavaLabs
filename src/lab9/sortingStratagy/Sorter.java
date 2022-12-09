package lab9.sortingStratagy;

import lab9.Student;

import java.util.ArrayList;
import java.util.TreeSet;

public interface Sorter {
    TreeSet<Student> sortData(ArrayList<Student> students, int course);
}
