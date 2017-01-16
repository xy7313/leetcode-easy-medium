public class Solution {
    public String frequencySort(String s) {
        if(s==null) return null;
        Map<Character,Integer> m = new HashMap<>();
        int max = 0;
        for(char c: s.toCharArray()){
            if(m.containsKey(c)){
                m.put(c,m.get(c)+1);
            }
            else m.put(c,0);
            // if(!m.containsKey(c)){
            //     m.put(c,0);
            // } 
            // m.put(c,m.get(c)+1);
            //如果这里的map构造形式写成上面两行注释中的代码，那下面 最外层for和最内层for也要从>=,<=变成>,<
            //for(int i = array.length-1; i>0; i--){
            //for(int j = 0; j<i; j++){
            max = Math.max(max,m.get(c));
        }
        List<Character>[] array = buildArray(m,max);
        StringBuilder sb = new StringBuilder();
        for(int i = array.length-1; i>=0; i--){
            List<Character> list = array[i];
            if(list!=null){
                for(Character c: list){
                    for(int j = 0; j<=i; j++){
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
    private List<Character>[] buildArray(Map<Character,Integer> map, int max){
        List<Character>[] arr = new List[max+1];
        for(Character c : map.keySet()){
            int count = map.get(c);
            if(arr[count]==null) arr[count] = new ArrayList<Character>();
            arr[count].add(c);
        }
        return arr;
    }
}