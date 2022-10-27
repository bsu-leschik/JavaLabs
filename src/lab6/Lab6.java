package lab6;

public class Lab6 {
    public static void main(String[] args){
        if (args.length == 0){
            ButtonControllerWindow w = new ButtonControllerWindow();
            w.showWindow();
            return;
        }
        TryClickButtonWindow window = new TryClickButtonWindow();
        window.showWindow();
    }
}
