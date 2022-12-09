package lab10.visitor;

public interface Element {

    default void accept(Visitor visitor){
        visitor.visit((MyString) this);
    }
}
