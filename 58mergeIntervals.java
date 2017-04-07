/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> after = new ArrayList<Interval>();
        if(intervals==null || intervals.size()==0) return after;

        //sort interval: intervals 调用而不再是Collections了，（）中只需要一个lambda表达式这一个参数
        intervals.sort((o1,o2)->(o1.start-o2.start));
        /*
        before java8
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval obj0, Interval obj1) {
                return obj0.start - obj1.start;
            }
        });
        */
        int curStart = intervals.get(0).start;
        int curEnd = intervals.get(0).end;
        for(Interval i : intervals){
            if(i.start<=curEnd){
                //max,eg:[[1,4],[2,3]] output [1,4] not [1,3]
                curEnd = Math.max(curEnd,i.end);
            }else{
                after.add(new Interval(curStart,curEnd));
                curStart = i.start;
                curEnd = i.end;
            }
        }
        //如果add放在for-loop里最后一句，会重复添加第一个，这样用两次add可以解决这个问题
        after.add(new Interval(curStart,curEnd));
        return after;
    }
}