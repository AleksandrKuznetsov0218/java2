package lesson2;


public class Main {

    public static int sum = 0;

    public static String[][] words1 = {
            {"10", "35", "12", "4"},
            {"3", "15", "14"},
            {"16", "22", "10", "21"}
    };

    public static String[][] words2 = {
            {"10", "35", "12", "4"},
            {"3", "15", "14", "16"},
            {"16", "22", "10", "21"}
    };

    public static String[][] words3 = {
            {"10", "35", "12", "4"},
            {"3", "15", "14", "16"},
            {"16", "f", "10", "21"},
            {"0", "22", "12", "16"}
    };

    public static String[][] words4 = {
            {"10", "35", "12", "4"},
            {"3", "15", "14", "16"},
            {"16", "5", "10", "21"},
            {"0", "22", "12", "16"}
    };

    public static void main(String[] args) {
        exceptionHandling(words1);
        exceptionHandling(words2);
        exceptionHandling(words3);
        exceptionHandling(words4);
    }

    public static void exceptionHandling(String[][] words) {
        try {
            sum = arrayDimension(words);
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            System.out.println("MyArrayDataException " + e.row + " " + e.column);
            e.printStackTrace();
        }
        System.out.println(sum);
    }


    public static int arrayDimension(String[][] words) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (words.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < 4; i++) {
            if (words[i].length != 4) throw new MyArraySizeException();
            for (int j = 0; j < words[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(words[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }

        }
        return sum;
    }
}






