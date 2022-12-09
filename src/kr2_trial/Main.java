package kr2_trial;

import kr2_trial.MVC.View;
import kr2_trial.stack.Stack;

public class Main {

    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(1);
        View window = new View(stack);
        window.setVisible(true);
    }
}
