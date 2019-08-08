package lesson4;

import java.util.Random;

public class AlsoSprichtTuring {
    public String alsoSprichtTuring() {
        String[] phrases = {"В самом деле?","Да что Вы говорите?",
                "Как интересно...","Да, мне приходилось об этом слышать.",
                "Возможно Вы правы","Очень интересная точка зрения","Вы полагаете?",
                "Возможно","Допускаю","САМ ДУРАК!"};
        Random random = new Random();
        return phrases[random.nextInt(10)];
    }
}
