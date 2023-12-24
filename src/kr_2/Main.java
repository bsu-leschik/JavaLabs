package kr_2;

import kr_2.MVC.View;
import kr_2.stack.Stack;

public class Main {

    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(1);
        View window = new View(stack);
        window.setVisible(true);
    }
}
