package kr_2.MVC;

import kr_2.stack.Stack;

import java.util.StringTokenizer;

public class Controller {

    Stack stackModel;

    String sizeModel;

    View view;
    public Controller(Stack stackModel, View view) {
            this.stackModel = stackModel;
            this.view = view;
    }

    public void setSizeModel(String sizeModel) {
        this.sizeModel = sizeModel;
        view.count.setText(sizeModel);
        view.repaint();
    }

    public void addElements(String line) throws NumberFormatException{
        StringTokenizer tokenizer = new StringTokenizer(line," ");
        while (tokenizer.hasMoreTokens()){
            stackModel.push(Integer.parseInt(tokenizer.nextToken()));
        }
        view.stack.setText(stackModel.toString());
        view.repaint();
    }

    public void removeElements(int amount) {
        for (int i = 0; i < amount; i++) {
            stackModel.pop();
        }
        view.stack.setText(stackModel.toString());
        view.repaint();
    }
}
