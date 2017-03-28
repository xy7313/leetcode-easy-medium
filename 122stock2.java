public int maxProfit(int[] prices) {
        int mp = 0;
        for(int i = 0; i<prices.length-1; i++){
            if(prices[i+1]-prices[i]>0){
                mp+=(prices[i+1]-prices[i]);
            }
        }
        return mp;
    }