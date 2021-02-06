public class Question1649 {
    /**
     * 寻找一个数字，保留最低位的1，其他位都是0
     * @param x 目标数
     * @return rt
     * */
    private int lowBit(int x){
        return x&(-x);
    }

    /**
     * 计算树状数组中的前缀和，即原数组中[0, position]之和
     * @param nums 树状数组
     * @param position 前缀和的右边界
     * @return 前缀和
     * */
    private long prefixSum(int[] nums, int position){
        long sum = 0;
        while(position!=0){
            sum += nums[position];
            position = position - lowBit(position);
        }
        return sum;
    }

    /**
     * 寻找树状数组中，取值在[lower_bound, upper_bound]之间的数的数量
     * @param numbers 树状数组，每一个元素
     * @param lower_bound 下边界
     * @param upper_bound 上边界
     * @return 这个范围内有多少数
     * */
    private long countNumber(int[] numbers, int lower_bound, int upper_bound){
        return prefixSum(numbers, upper_bound)-prefixSum(numbers, lower_bound-1);
    }

    /**
     * 插入一个数到树状数组中
     * @param numbers 目标树状数组，numbers[i]是取值为[i-lowbit(i)+1,i]的数有多少
     * @param new_number 新插入的数字
     * */
    private void insertNumber(int[] numbers, int new_number){
        int current = new_number;
        while(current<numbers.length){
            numbers[current] += 1;
            current += lowBit(current);
        }
    }

    /**
     * 打印一个数组
     *
     * */
    private void printArray(int[] numbers){
        StringBuilder result = new StringBuilder();
        for(int i:numbers){
            result.append(i).append(",");
        }
        result.deleteCharAt(result.length()-1);
        result.append("]");
        result.insert(0,"[");
        System.out.println(result.toString());
    }

    /**
     * 按照instructions的顺序一个个把里面的数插入到另一个数组中是，插入的成本是数组中严格大于此元素的数与严格小于这个数的数量的较小值
     * @param instructions 目标指令数组
     * */
    public int createSortedArray(int[] instructions) {
        long result = 0;
        int[] numbers = new int[100002];
        for(int i:instructions){
            long lower_than = i==1?0:countNumber(numbers, 1, i-1);
            long greater_than = i==100000?0:countNumber(numbers, i+1, 100000);
            result += Math.min(lower_than, greater_than);
            result %= (1e9+7);
            insertNumber(numbers, i);
        }
        return (int)(result%(1e9+7));
    }
}
