121. Best Time to Buy and Sell Stock 
122. Best Time to Buy and Sell Stock II



####122. Best Time to Buy and Sell Stock II
最重要的思路，可以多次买卖，所以maxprofit=max每次买卖profit，每次有profit就卖，累计的也更多。所以核心代码就一句话`if(prices[i+1]-prices[i]>0) mp+=(prices[i+1]-prices[i]);`
