package kr2_trial.MVC;

import kr2_trial.Strategy.Strategy;
import kr2_trial.set.Set;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class Controller {

    Set setModel;

    String sizeModel;

    View view;
    public Controller(Set setModel, View view) {
            this.setModel = setModel;
            this.view = view;
    }

    public void addElements(String line) throws NumberFormatException{
        StringTokenizer tokenizer = new StringTokenizer(line," ");
        while (tokenizer.hasMoreTokens()){
            setModel.add(Integer.parseInt(tokenizer.nextToken()));
        }
        view.set.setText(setModel.toString());
        view.binarySet.setText(setModel.binaryToString());
        view.repaint();
    }

    public void saveToFile(File file) throws IOException {
        if (file == null){
            return;
        }
        setModel.save(file);
        view.repaint();
    }

    public void setSizeCalculatorAndCalculate(Strategy calculator) {
        setModel.setCounter(calculator);
        view.count.setText(String.valueOf(setModel.cardinality()));
    }
}
