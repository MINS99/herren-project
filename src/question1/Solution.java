package question1;

import java.util.List;

public class Solution {
    public String solution(List<Integer> nums) {
        StringBuilder result = new StringBuilder();

        if (nums.isEmpty()) {
            return result.toString();
        }

        int pivot = nums.get(0);
        for (int i = 0; i < nums.size(); i++) {
            if (i != nums.size() - 1 && (nums.get(i) + 1 == nums.get(i + 1))) {
                continue;
            }

            if (pivot != nums.get(i)) {
                result.append(pivot)
                        .append("~")
                        .append(nums.get(i));
            } else {
                result.append(nums.get(i));
            }

            if (i != nums.size() - 1) {
                result.append(", ");
                pivot = nums.get(i + 1);
            }
        }

        return result.toString();
    }
}
