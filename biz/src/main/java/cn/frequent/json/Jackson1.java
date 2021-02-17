package cn.frequent.json;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by leslie on 2020/12/1.
 */
public class Jackson1 {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1() throws JsonProcessingException {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("name", "zhang");
        hashMap.put("sex", "1");
        hashMap.put("login", "Jack");
        hashMap.put("password", "123abc");
        hashMap.put("local_execute", true);

        String userMapJson = mapper.writeValueAsString(hashMap);
        System.out.println(userMapJson);

        JsonNode node = mapper.readTree(userMapJson);
        System.out.println(node.get("password").asText());

        // 输出不转意,输出结果会包含""，这是不正确的，除非作为json传递，如果是输出结果值，必须如上一行的操作
        System.out.println(node.get("name"));
    }

    private static void test2() throws JsonProcessingException {
        System.out.println("======test2=====");
        String str = "{\"data\":{\"birth_day\":7,\"birth_month\":6},\"errcode\":0,\"msg\":\"ok\",\"ret\":0}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(str);
        // 符合字段 textValue(), asText() 都不能输出字符串. toString()可以.
        System.out.println(root.path("data").textValue());
        System.out.println(root.path("data").asText());
        System.out.println(root.path("data").toString());
        System.out.println(root.path("data1").isMissingNode());
        // birth_day 是整数，不能用textValue.
        System.out.println(root.path("data").path("birth_day").textValue());
        System.out.println(root.path("data").path("birth_day").intValue());
    }

    private static void test3() throws JsonProcessingException {
        System.out.println("====test3=====");
        String str = "{\"data\":{\"birth_day\":7,\"birth_month\":6},\"errcode\":0,\"msg\":\"ok\",\"ret\":0}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(str);

        // 新增.
        ObjectNode data = (ObjectNode) root.path("data");
        data.put("birth_year", 1997);
        System.out.println(root.toString());

        // 删除
        data.remove("birth_day");
        System.out.println(root.toString());

        // 合并属性.
        String addToBirthDayStr = "{\"name\":\"wy\", \"age\":22}";
        JsonNode addToBirthDay = mapper.readTree(addToBirthDayStr);
        data.setAll((ObjectNode) addToBirthDay);
        System.out.println(root.toString());
    }

    private static void test4() throws JsonProcessingException {
        System.out.println("=====test4=====");
        ArrayNode arr = mapper.createArrayNode();
        arr.add("a");
        arr.add("b");

        String str = "{\"data\":{\"birth_day\":7,\"birth_month\":6},\"errcode\":0,\"msg\":\"ok\",\"ret\":0}";
        JsonNode root = mapper.readTree(str);
        ((ObjectNode) root).set("arr", arr);
        System.out.println(root.toString());
    }
}
