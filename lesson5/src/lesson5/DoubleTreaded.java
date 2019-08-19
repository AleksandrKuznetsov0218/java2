package lesson5;

public class DoubleTreaded {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    float[] arr1 = new float[h];
    float[] arr2 = new float[h];

    public void countingTime() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5)
                        * Math.cos(0.2f + i / 5) * Math.cos(0.4f + (i / 2)));
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr[i] = (float) (arr2[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5)
                        * Math.cos(0.4f + (i + h) / 2));

            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a + " мс");
        System.out.println(arr[h]);
    }
}