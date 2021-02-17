package cn;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Date;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.SerializationUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by leslie on 2018/5/16.
 */
public class Test {

    @org.junit.Test
    public void test1() {
        Float a = 1234567000.12f;
        DecimalFormat b = new DecimalFormat("0.00");
        System.out.println(String.valueOf(a));
        System.out.println(b.format(a));

        BigDecimal c = new BigDecimal("123456789000.00");
        System.out.println(c);
    }

    /*
     * @org.junit.Test public void test2() {
     * System.out.println(org.apache.commons.lang3.StringUtils.containsAny("aaaaaa", "aaaaaaa", "b"));
     * System.out.println(org.apache.commons.lang3.StringUtils.containsAny("200", "500", "600")); }
     */

    @org.junit.Test
    public void test3() {
        String[] cards = { "6217001210024455220", "6217001210024455221", "6228483868586908177", "6228483868586908178",
                           "6214855716759598", "6228480322776602714" };
        for (String card : cards) {
            System.out.println(card + ": " + matchLuhn(card));
        }
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     * 
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i = 0; i < cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    @org.junit.Test
    public void test4() {
        Set<String> a = new HashSet<>();
        a.add("/a/b/c_@");
        a.add("/a/b/c_");
        a.add("/a/b/c");
        a.add("/a/b/c/d");
        a.add("/a/b/caaa_@");
        a.add("/a/b/caaa_@1");
        a.add("/a/b/caaa_@81");
        a.add("/");
        a.add("/a");
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b/c")));
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b")));
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b/caaa")));
        System.out.println(a.stream().anyMatch(s -> s.split("_@")[0].equals("/a/b/caaa_")));
        // System.out.println(a.contains("/a/b/c_@"));
        // System.out.println(a.contains("/a/b/c_@1"));
        // System.out.println(a.contains("/a/b/c"));
        // System.out.println(a.contains("/a/b"));
        // System.out.println(a.contains("/a"));

        String url1 = "/a/b/caaa_@";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/caaa_@1";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/caaa_@123";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/c";
        System.out.println(url1.split("_@")[0]);
        url1 = "/";
        System.out.println(url1.split("_@")[0]);
        url1 = "/a/b/caaa_";
        System.out.println(url1.split("_@")[0]);

    }

    @org.junit.Test
    public void test5() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }

    @org.junit.Test
    public void test6() {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        // a.add("c");
        int i = 0;
        try {
            for (String s : a) {
                a.remove(s);
            }
        } catch (Exception e) {
            throw (e);
        }
    }

    @org.junit.Test
    public void test7() {
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // String sql = "select * from bond.credit_nfeder_tester_op_detail_c750b55e1a5311eba47b1c1b0d9c0b58";
        String sql = "select * from bond.credit_nfeder_tester_op_roc_c750b57f1a5311eba47b1c1b0d9c0b58";
        try (Connection conn = getConnection();
                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);) {

            List<Map> hiveResult = convertResultSetToList(resultSet);
            System.out.println(hiveResult.size());
        } catch (Exception e) {
            throw new RuntimeException("执行SQL查询语句失败", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hive2://" + "10.57.239.33:10000" + "/" + "default", "", "");
    }

    public List<Map> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map> list = new ArrayList<>();
        while (rs.next()) {
            HashMap<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                String columnName = extractColumnName(md.getColumnName(i));
                row.put(columnName, rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

    private static String extractColumnName(String columnName) {
        columnName = columnName.contains(".") ? columnName.split("\\.")[1] : columnName;
        return columnName;
    }

    @org.junit.Test
    public void test8() {
        writeCsv();
    }

    private void writeCsv() {
        String fileName = "1.csv";// 文件名称
        String filePath = "/Users/leslie/Temp1/2020/0709/f/"; // 文件路径

        // 表格头
        Object[] head = { "客户姓名", "证件类型", "日期", };
        List<Object> headList = Arrays.asList(head);

        // 数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for (int i = 0; i < 100; i++) {
            rowList = new ArrayList<Object>();
            rowList.add("张三" + i);
            rowList.add("263834194" + i);
            rowList.add(new Date());
            dataList.add(rowList);
        }

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);

            // 文件下载，使用如下代码
            /*
             * response.setContentType("application/csv;charset=gb18030"); response.setHeader("Content-disposition",
             * "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8")); ServletOutputStream out =
             * response.getOutputStream(); csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);
             */

            int num = headList.size() / 2;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num; i++) {
                buffer.append(" ,");
            }
            csvWtriter.write(buffer.toString() + fileName + buffer.toString());
            csvWtriter.newLine();

            // 写入文件头部
            writeRow(headList, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

    @org.junit.Test
    public void test9() throws DecoderException {
        HashMap<String, String> data = new HashMap<>();
        data.put("party_id", "12");
        String r = Hex.encodeHexString((SerializationUtils.serialize(data)));
        System.out.println(r);
        System.out.println(SerializationUtils.deserialize(Hex.decodeHex(r)));
        String arbiterPartyId = "9999";
        String federationInfo = "{\"local\":{\"role\":\"arbiter\",\"party_id\":\"9999\"}, \"role\": {\"host\": [\"10000\"], \"guest\": [\"10001\"], \"arbiter\": [\"9999\"] }}";
        System.out.println(federationInfo.replace("["+arbiterPartyId+"]", "[\"" + arbiterPartyId + "\"]"));
        System.out.println(federationInfo.replace("["+arbiterPartyId+"]", "[\"" + arbiterPartyId + "\"]"));

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("test_id", "8c743c5e65574af4b84cf841f3a014a4");
//        map1.put("model_id", "");
//        map1.put("party_id", "10000");
        map1.put("account", "yang.wang");
//        map1.put("org_code", "OrgA");
//        map1.put("status", "[{\"org_code\":\"DiSanFang\",\"status\":\"testing\"},{\"org_code\":\"OrgA\",\"status\":\"testing\"},{\"org_code\":\"TongDun\",\"status\":\"testing\"}]");
        r = Hex.encodeHexString((SerializationUtils.serialize(map1)));
        System.out.println(r);

        // 解码
        String a = "aced0005737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000c74000c70726f6a6563745f75756964740020306166333033313533313733346139393864643533343762656234626662383074000b6461675f7461736b5f6964737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d030006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e69747564657400025b42787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870fffffffffffffffffffffffefffffffe00000001757200025b42acf317f8060854e002000078700000000202b1787400086461675f6e616d6574000ce6a0b7e69cace5afb9e9bd907400086461675f75756964740020373734643461633864633962343265646262626332646564343332353336643074000f6e6565645f6d756c74697061727479737200116a6176612e6c616e672e426f6f6c65616ecd207280d59cfaee0200015a000576616c75657870017400116f70657261746f725f6f72675f636f64657400044f7267417400066461675f69647372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c75657871007e000700000000000002087400047575696474002062383336386230306336316234333962623935323764336438376565336464317400086f70657261746f7274000561646d696e74000974696d657374616d707372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000177289aa714787400116461675f74656d706c6174655f636f64657400146665645f73616d706c655f616c69676e5f64616774001273796e634f70657261746f72506172616d73737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000057704000000057371007e00003f4000000000000c77080000001000000003740004636f64657400066d6574686f6474000d6f70657261746f725f636f646574000f73616d706c655f616c69676e5f6f7074000576616c756574000e617262697465725f617373697374787371007e00003f4000000000000c7708000000100000000371007e0024740008646f5f616c69676e71007e002674000f73616d706c655f616c69676e5f6f7071007e002874000454727565787371007e00003f4000000000000c7708000000100000000371007e002474000e6d696e5f73616d706c655f6e756d71007e002674000f73616d706c655f616c69676e5f6f7071007e0028740003323030787371007e00003f4000000000000c7708000000100000000371007e002474000f646174615f62617463685f73697a6571007e002674000f73616d706c655f616c69676e5f6f7071007e00287400083130303030303030787371007e00003f4000000000000c7708000000100000000371007e0024740011656e6372797074696f6e5f6d6574686f6471007e002674000f73616d706c655f616c69676e5f6f7071007e0028740003616573787878";
        System.out.println(SerializationUtils.deserialize(Hex.decodeHex(a)));

    }

    @org.junit.Test
    public void test10() throws JsonProcessingException {
        float a = 500.2323f;
        System.out.println(Math.floor(a));
        System.out.println(String.valueOf(a).replaceAll("\\..*", "%"));

        String b = "{\"local\":{\"role\":\"guest\",\"party_id\":10000}, \"role\": {\"host\": [], \"guest\": [10001,10000], \"arbiter\": [9999] }}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(b);
        JsonNode localPartyIdJson = root.at("/local/party_id");
        System.out.println(localPartyIdJson.toString());
        if (!localPartyIdJson.isMissingNode()) {
            ((ObjectNode) root.get("local")).put("party_id", String.valueOf(localPartyIdJson.toString()));
        }
        JsonNode guestArrJson = root.at("/role/guest");
        if (!guestArrJson.isMissingNode()) {
            ArrayNode arr = processArrayPartyIds(guestArrJson);
            ((ObjectNode) root.get("role")).set("guest", arr);
        }
        JsonNode hostArrJson = root.at("/role/host");
        if (!hostArrJson.isMissingNode()) {
            ArrayNode arr = processArrayPartyIds(hostArrJson);
            ((ObjectNode) root.get("role")).set("host", arr);
        }
        JsonNode arbiterArrJson = root.at("/role/arbiter");
        if (!arbiterArrJson.isMissingNode()) {
            ArrayNode arr = processArrayPartyIds(arbiterArrJson);
            ((ObjectNode) root.get("role")).set("arbiter", arr);
        }
        System.out.println(root.toString());
    }

    private ArrayNode processArrayPartyIds(JsonNode arr) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode newArr = mapper.createArrayNode();
        if (arr.isMissingNode()) {
            return newArr;
        }
        for (JsonNode node : arr) {
            newArr.add(String.valueOf(node.toString()));
        }
        return newArr;
    }


}
