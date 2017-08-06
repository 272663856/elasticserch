package src.com.es;

/**
 * Created by wangjy on 2017/7/23.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

import static src.com.es.ElasticsearchTools.getDocuments;
import static src.com.es.ElasticsearchTools.updateDocument;


/**
 * 测试类
 */
public class ElasticsearchTest {

    public final static String index="member";
    public final static String type="user";

    private final static Logger logger = LoggerFactory.getLogger(ElasticsearchTest.class);
    public static void main(String args[]) throws UnknownHostException {
//        queryTest();

        addTest();
//      ElasticsearchTools.testScrolls();

    }

    public static void queryTest(){
        //测试查询方法
        Map<Object, Object> queryMaps = new HashMap<Object, Object>();
        queryMaps.put("type", "type"+1);
        Map<Object, Object> fullTextQueryMaps = Maps.newHashMap();
        fullTextQueryMaps.put("name", "毛主席1");

        // 范围参数
        List<Map<Object, Object>> rangeLists = new ArrayList<Map<Object, Object>>();
        Map<Object, Object> rangeMaps = new HashMap<Object, Object>();
        rangeMaps.put("field", "age");
        rangeMaps.put("from", "1");
        rangeMaps.put("to", "30");
        rangeLists.add(rangeMaps);
        Map<Object, Object> sortMaps = new HashMap<Object, Object>();
        sortMaps.put("age", "ASC");
        /*高亮字段*/
        List<String> fields = new ArrayList<String>();
        fields.add("name");
        fields.add("desc");
        List<Map<String, Object>> lists = ElasticsearchTools.queryDocuments(index, type, 0, 10, rangeLists, queryMaps, sortMaps, fields, fullTextQueryMaps);
        System.out.println(JSON.toJSONString(lists));
    }

    public static void  addTest(){
//        updateDocument("member", "user", "1", "message", "我真的爱过啊！");
//        getDocuments("member", "user", "1", "2");
        //批量新增方法
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        for(int i=0;i<1000Format df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String pattern = "yyyy-MM-dd'T'HH:mm:ss:SSSZZ";
            map.put("id", i);
            map.put("desc", "北京天安门"+i);
            map.put("name", "毛主席"+i);
            map.put("type", "type"+i);
            map.put("age", 20+i);
            map.put("mydate", df.format(new Date()));
            map.put("birthday", DateFormatUtils.format(new Date(), pattern));
            map.put("love", "哈哈哈哈哈"+1);
            list.add(map);
        }
        logger.info("size={}",list.size());
        logger.info("==========start==============");
        ElasticsearchTools.addDocuments(list, index, type);
        logger.info("==========end==============");
    }
}
