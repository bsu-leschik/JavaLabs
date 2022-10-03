package lab4;

public class Start {
    public static void main(String[] args){
        testsWithInts();
        System.out.println("------------------------------------------------");
        testsWithNumbers();
    }

    public static void testsWithInts(){
        BinaryTree<Integer> binaryTree = new BinaryTree<>(15);
        binaryTree.addElement(13);
        binaryTree.addElement(125);
        binaryTree.addElement(-7);
        binaryTree.addElement(-54);
        binaryTree.addElement(6);
        binaryTree.addElement(3);
        binaryTree.addElement(14);
        binaryTree.addElement(100);
        binaryTree.addElement(95);
        binaryTree.addElement(200);
        binaryTree.addElement(112);
        binaryTree.addElement(110);
        binaryTree.addElement(171);
        binaryTree.addElement(250);
        binaryTree.goPLR();
        binaryTree.goLRP();
        binaryTree.goLPR();
        System.out.println(binaryTree.findElement(4));
        System.out.println(binaryTree.findElement(6).element);
    }

    public static void testsWithNumbers(){
        BinaryTree<Number> binaryTree = new BinaryTree<>(new Number(15));
        binaryTree.addElement(new Number(13));
        binaryTree.addElement(new Number(125));
        binaryTree.addElement(new Number(-7));
        binaryTree.addElement(new Number(-54));
        binaryTree.addElement(new Number(6));
        binaryTree.addElement(new Number(3));
        binaryTree.addElement(new Number(14));
        binaryTree.addElement(new Number(100));
        binaryTree.addElement(new Number(95));
        binaryTree.addElement(new Number(200));
        binaryTree.addElement(new Number(112));
        binaryTree.addElement(new Number(110));
        binaryTree.addElement(new Number(171));
        binaryTree.addElement(new Number(250));
        binaryTree.goPLR();
        binaryTree.goLRP();
        binaryTree.goLPR();
        System.out.println(binaryTree.findElement(new Number(4)));
        System.out.println(binaryTree.findElement(new Number(6)).element);
    }
}
