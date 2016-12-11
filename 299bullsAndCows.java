public class Solution {
    public String getHint(String secret, String guess) {
        int a = 0;
        int b = 0;
        int[] numbers = new int[10];
        for( int i =0; i<secret.length();i++){
            if(secret.charAt(i) == guess.charAt(i)){
                a++;
            }else{
                 if (numbers[secret.charAt(i)-'0'] <0) b++;
                    numbers[secret.charAt(i)-'0']++;
                 if (numbers[guess.charAt(i)-'0'] >0) b++;
                     numbers[guess.charAt(i)-'0']--;
            }
        }
        return a+"A"+b+"B";        
    }
}