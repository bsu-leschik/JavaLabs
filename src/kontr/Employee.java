package kontr;

public abstract class Employee {
    protected String surname;
    protected final String organization;
    protected final double coefficient;

    public Employee(String surname, String organization, double coefficient){
        if (surname == null || organization == null){
            throw  new NullPointerException("Arguments cannot be null");
        }

        if (coefficient <= 0){
            throw new IllegalArgumentException("Coefficient cannot bo less or equals 0");
        }

        this.surname = surname;
        this.organization = organization;
        this.coefficient = coefficient;

    }


    public abstract int getSalary();

    public double getCoefficient() {
        return coefficient;
    }

    public String getOrganization() {
        return organization;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "surname='" + surname + '\'' +
                ", organization='" + organization + '\'' +
                ", coefficient=" + coefficient +
                '}';
    }
}
