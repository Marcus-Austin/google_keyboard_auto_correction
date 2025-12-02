import java.util.List;

public class App {

    public static int LevenshteinDistance(String word1, String word2){
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 2];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    dp[i][j] = j; 
                } else if (j == 0) {
                    dp[i][j] = i; 
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; 
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], 
                                    Math.min(dp[i][j - 1], 
                                             dp[i - 1][j - 1])); 
                }
            }
        }
        return dp[len1][len2];
    }
    public static String correctWords(List<String> dictionary, String wordDigit){
        if(dictionary.contains(wordDigit)){
            return wordDigit;
        }

        String correctWord = null;
        int minorDistance = 3;
        for (String word : dictionary) {
            int distance = LevenshteinDistance(wordDigit, word);
            if (distance < minorDistance) {
                minorDistance = distance;
                correctWord = word;
            }
        }
        return correctWord;
    }
    public static void main(String[] args) throws Exception {
    List<String> dictionary = List.of("casa","cama","carro","gato","pato");
    String[] test = {"casa","caso","gatu","xyz"};
     for(String word : test){
        System.out.println("output: " +word+" -> "+correctWords(dictionary, word));
     }
    }
}
