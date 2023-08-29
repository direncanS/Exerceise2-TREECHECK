public class BSTStats {

    private boolean isAVL;
    private int minElement;
    private int maxElement;
    private int elementCount = 0;
    private double average;
    private int total = 0;


    public BSTStats(){
        isAVL = true;
        minElement = Integer.MAX_VALUE;
        maxElement = Integer.MIN_VALUE;
        average = 0.0;
    }


    public boolean isAVL() {
        return isAVL;
    }

    public void setAVL(boolean AVL) {
        isAVL = AVL;
    }

    public int getMinElement() {
        return minElement;
    }

    public void setMinElement(int key) {
        if(key < minElement){
            minElement = key;
        }
    }

    public int getMaxElement() {
        return maxElement;
    }

    public void setMaxElement(int key) {
        if(key > maxElement){
            maxElement = key;
        }
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void addElement(int key)
    {
        total += key;
        elementCount++;
    }

    public double calculateAverage()
    {
        average = (double) total/elementCount;
        return average;
    }
}
