public class Solution {
    public boolean canWinNim(int n) {
        return n%4!=0;
    }
}

/*
刚刷题的时候觉得很难想的一道题，
后来想通之后觉得好简单。。

最后remove石头的是winner，
也就是说当前石头堆不论前面怎么拿只要有给我剩石头，我就能赢
也就是说，每次不论对手拿几个，我都跟他一起凑成4
（比如对手：1，我3；对手2，我2）
这样只要石头堆对4取余有剩余，
剩余的就是我的，就赢了

*/