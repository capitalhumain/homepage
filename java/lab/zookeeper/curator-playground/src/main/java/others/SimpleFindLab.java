package others;

import java.util.*;

public class SimpleFindLab {
    public static void main(String[] args) {
        String[] target = new String[] {"code", "software"};
        String sourceString = "This book is a useful companion for anyone learning to write clean Java code. The authors introduce you to the fundamentals of becoming a software craftsman, by comparing pieces of problematic code with an improved version, to help you to develop a sense for clean code. This unique before-and-after approach teaches you to create clean Java code.";
        List<String> bagsOfWords = new ArrayList<>();

        for(String token : sourceString.split(" ")) {
            bagsOfWords.add(token.replace(".", "").toLowerCase());
        }

//        bagsOfWords.forEach(word -> System.out.print(word + " "));
//        System.out.println();

        Map<String, Set<Integer>> targetPositionResult = new HashMap<>();
        for(String targetItem : target) {
            targetPositionResult.put(targetItem, new HashSet<>());
        }

        int index = 0;
        while(index != bagsOfWords.size()-1) {
            for(String targetItem: target) {
                if(targetItem.equals(bagsOfWords.get(index))) {
                    targetPositionResult.get(targetItem).add(index);
                }
            }
            index++;
        }

        targetPositionResult.forEach((k, v) -> {
            System.out.print(k + ": ");
            v.forEach(pos -> System.out.print(pos + " "));
            System.out.println();
        });
    }
}
