package lesson5;

public class DoubleTreaded {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    float[] arr1=new  float[h];
    float[] arr2=new  float[h];

    public void countingTime() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0,arr1, 0, h-1);
        System.arraycopy(arr, h+1,arr2, 0, h-1);
        for (int i = 0; i < h; i++) {
            arr[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            arr[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
            
        }
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a+" мс");
    }
}