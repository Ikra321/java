public class Task {
    public static class FrequentChar {
        public char[] uniqueChar;
        public int[] count;
        public int uniqueCount;

        public FrequentChar(char[] uniqueChar, int[] count, int uniqueCount) {
            this.uniqueCount = uniqueCount;
            this.count = count;
            this.uniqueChar = uniqueChar;
        }
    }

    public static FrequentChar frequencyArray(String text) {
        int len = text.length();
        char[] uniqueChars = new char[len];
        int[] counts = new int[len];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ')
                continue;
            
            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueChars[j] == c) {
                    index = j;
                    break;
                }
            }

            if (index != -1) {
                counts[index]++;
            } else {
                uniqueChars[uniqueCount] = c;
                counts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        return new FrequentChar(uniqueChars, counts, uniqueCount);
    }

    public static int findMaxCount(int[] counts, int uniqueCount) {
        int maxCount = 0;
        for (int i = 0; i < uniqueCount; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
            }
        }
        return maxCount;
    }

    public static void printCharsWithCount(char[] uniqueChars, int[] counts, int uniqueCount, int maxCount) {
        System.out.println("Наиболее часто встречающиеся символы (" + maxCount + "):");

        for (int i = 0; i < uniqueCount; i++) {
            if (counts[i] == maxCount) {
                System.out.print("'" + uniqueChars[i] + "'");
            }
        }
        System.out.println();
    }

    public static void printResult(String text) {
        if (text.length() == 0) {
            System.out.println("Текст не был введен.");
            return;
        }

        FrequentChar freqArr = frequencyArray(text);
        int maxCount = findMaxCount(freqArr.count, freqArr.uniqueCount);
        printCharsWithCount(freqArr.uniqueChar, freqArr.count, freqArr.uniqueCount, maxCount);
    }
}