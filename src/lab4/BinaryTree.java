package lab4;

public class BinaryTree <T extends Comparable<T>>{
    class Node{
        T element;
        Node left;
        Node right;

        Node(T element){
            this.element = element;
        }

        public void addLeft(T element){
            left = new Node(element);
        }

        public void addRight(T element){
            right = new Node(element);
        }
    }

    private T current;

    private Node firstNode;

    BinaryTree(T firstElement){
        firstNode = new Node(firstElement);
    }

    public void addElement(T element){
        current = element;
        if (firstNode != null) {
            addElementRec(firstNode);
        }
        else {
            firstNode = new Node(element);
        }
        current = null;
    }

    public Node findElement(T element){
        this.current = element;
        Node n = findElementRec(firstNode);
        this.current = null;
        return n;
    }

    public void goPLR(){
        goPLRRec(this.firstNode);
        System.out.println();
    }

    public void goLRP(){
        goLRPRec(this.firstNode);
        System.out.println();
    }

    public void goLPR(){
        goLPRRec(this.firstNode);
        System.out.println();
    }

    private void goLPRRec(Node last){
        if (last.left != null){
            goLPRRec(last.left);
        }

        System.out.print(last.element + " ");

        if (last.right != null){
            goLPRRec(last.right);
        }
    }

    private void goLRPRec(Node last){
        if (last.left != null){
            goLRPRec(last.left);
        }
        if (last.right != null){
            goLRPRec(last.right);
        }
        System.out.print(last.element + " ");
    }

    private void goPLRRec(Node last){
        System.out.print(last.element + " ");
        if (last.left != null){
            goPLRRec(last.left);
        }
        if (last.right != null){
            goPLRRec(last.right);
        }
    }

    private Node findElementRec(Node node){
        if (node == null){
            return null;
        }

        int compareResult = node.element.compareTo(current);

        if (compareResult > 0){
            return findElementRec(node.left);
        } else if (compareResult < 0) {
            return findElementRec(node.right);
        }
        else {
            return node;
        }

    }

    private Node addElementRec(Node currentNode){
        int compareResult = currentNode.element.compareTo(current);

        if (compareResult > 0){
            if (currentNode.left == null){
                currentNode.left = new Node(current);
                return currentNode.left;
            }

            return addElementRec(currentNode.left);
        } else if (compareResult < 0) {
            if (currentNode.right == null){
                currentNode.right = new Node(current);
                return currentNode.right;
            }

            return addElementRec(currentNode.right);
        }
        return null;
    }
}
