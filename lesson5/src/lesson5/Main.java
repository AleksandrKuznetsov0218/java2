package lesson5;

public class Main {

    public static void main(String[] args) {
//        Вроде правильно. Я не успел попробовать на четыре потока поделить,
//        но уверен, что скорость еще вырастет, несмотря на то, что у меня двухядерный камень.
        SingleThreaded st = new SingleThreaded();
        st.countingTime();
        DoubleTreaded dt = new DoubleTreaded();
        dt.countingTime();
    }
}
