package kr2_trial;

import kr2_trial.MVC.View;
import kr2_trial.set.Set;

public class KR {

    public static void main(String[] args){
        //MVC - активный, M - MVC.Model, V - MVC.View, C - set.Set
        Set set = new Set();
        set.add(1);
        View window = new View(set);
        window.setVisible(true);
    }
}
