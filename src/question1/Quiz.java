package question1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz {
    public static void main(String[] args) {
        int[] lists = {13, 14, 15, 16, 21, 23, 24, 25, 100};
        List<Integer> list = Arrays.stream(lists).boxed().collect(Collectors.toList());

        Solution solution = new Solution();
        String result = solution.solution(list);
        System.out.println(result);
    }
}
