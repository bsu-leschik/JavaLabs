package lab9;

public class Student implements Comparable<Student>{

    int identifier;
    String surname;
    int courseNumber;
    int groupNumber;

    public Student(int identifier, String surname, int courseNumber, int groupNumber){
        this.identifier = identifier;
        this.surname = surname;
        this.courseNumber = courseNumber;
        this.groupNumber = groupNumber;
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getSurname() {
        return surname;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "identifier=" + identifier +
                ", surname='" + surname + '\'' +
                ", courseNumber=" + courseNumber +
                ", groupNumber=" + groupNumber +
                '}';
    }
}
