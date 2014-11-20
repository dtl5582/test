import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class T {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\数据\\filter.txt");
        File fileout = new File("E:\\数据\\filter_out.txt" );
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        String s;
        String ss[];
        String ym;
        long pv=0;
        long count =0;
        while ((s = br.readLine()) != null) {
            try {
                ss=s.split("\t");
                ym=ss[1].split("/")[0];
                List<String> list=map.get(ym);
                if(list==null){
                    list=new ArrayList<String>();
                    map.put(ym, list);
                }
                list.add(s);
            } catch (Exception e) {
                // TODO: handle exception
            }
           System.out.println(++count);
        }
        
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true"); 
        for(Entry<String,List<String>> e: map.entrySet()){
            Collections.sort(e.getValue(), new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int a=o1.split("\t")[0].compareTo(o2.split("\t")[0]);
                    int b=o1.length()-o2.length();
                    return o2.compareTo(o1);
                }
            });
        }
        
        
        ArrayList<Entry<String, List<String>>> mappingList = new ArrayList<Map.Entry<String, List<String>>>(map.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String, List<String>>>() {
            public int compare(Map.Entry<String, List<String>> mapping1, Map.Entry<String, List<String>> mapping2) {
                return (int) mapping2.getValue().size()-mapping1.getValue().size();
            }
        });
        
        int i = 0;
        FileWriter fw = new FileWriter(fileout);
        for(Entry<String, List<String>> e:mappingList){
//            fw = new FileWriter("E:\\项目文档\\数据清洗方案\\清洗数据\\IP网\\out\\"+e.getKey().split(":")[0]+".txt");
            fw.write("------------------[begin] {"+ (++i) +"} ["+e.getKey()+" "+e.getValue().size()+"]-----------------------\r\n");
            List<String> list=e.getValue();
            for(String sf:list){
                fw.write(sf+"\r\n");
            }
            fw.write("------------------[end]-----------------------\r\n");
            fw.flush();
        }
        fw.close();
        
    }
    

}
