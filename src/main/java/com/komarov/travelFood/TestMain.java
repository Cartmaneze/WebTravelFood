package com.komarov.travelFood;

/**
 * Created by Никита on 04.09.2017.
 */
public class TestMain {

    public static void main(String[] args) {
//        List<String> names = Arrays.asList("John", "Daenerys", "Tyrion", "", null, "Arya");
//
//        names.stream()
//                .filter(a -> a != null)
//                .filter(a -> !a.isEmpty())
//                .filter(a -> a.contains("a"))
//                .sorted((a1, a2) -> a1.compareTo(a2))
//                .forEach(System.out::println);

//        int i = -980000000;
//        int y = -2147483647;
//        int z = Integer.MAX_VALUE;
//
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(Integer.toBinaryString(i >>> 16));
//        System.out.println(Integer.toBinaryString(i >> 16));
//
//        System.out.println(Integer.toBinaryString(y));
//        System.out.println(Integer.toBinaryString(y << 30));
//        System.out.println(Integer.toBinaryString(y >> 3));
//
//        System.out.println(binaryToInt("10000000000000000000000000000000"));

//        ArrayList<Integer> integerList = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            double a = (Math.random() * (10000-5000)) + 5000;
//            integerList.add((int) a);
//        }
//
//        integerList.forEach(System.out::println);
//
//        TestMain.sort(integerList);
//
//        integerList.forEach(System.out::println);
//
//        Iterator<Integer> integerIterator = integerList.iterator();
//
//        while (integerIterator.hasNext()) {
//            int number = integerIterator.next();
//            if (number % 2 == 1) {
//                integerIterator.remove();
//            }
//        }
//
//        integerList.forEach(System.out::println);
//
//    }
//
//    static int binaryToInt (String binary){
//        char []cA = binary.toCharArray();
//        int result = 0;
//        for (int i = cA.length-1;i>=0;i--){
//            //111 , length = 3, i = 2, 2^(3-3) + 2^(3-2)
//            //                    0           1
//            if(cA[i]=='1') result+=Math.pow(2, cA.length-i-1);
//        }
//        return result;
//    }
//
//    public static void sort(ArrayList<Integer> list) {
//        int gap = list.size();
//        boolean swapped = true;
//        while (gap > 1 || swapped) {
//            if (gap > 1)
//                gap = (int) (gap / 1.247330950103979);
//
//            int i = 0;
//            swapped = false;
//            while (i + gap < list.size()) {
//                if (list.get(i) > list.get(i + gap)) {
//                    int t = list.get(i);
//                    list.set(i, list.get(i + gap));
//                    list.set(i + gap, t);
//                    swapped = true;
//                }
//                i++;
//            }
//        }
//    }


    }

}
