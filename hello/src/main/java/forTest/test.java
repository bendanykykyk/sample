package forTest;


import com.alibaba.fastjson.JSONObject;
/**
 * 用于jmeter测试编写，自动帮助生产http请求中参数的封装
 */
public class test {
    public static String toMyString(String jsonString){
        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        System.out.println(jsonObject);
        Object arr[] = jsonObject.keySet().toArray();

        //转化json对象中的值
        for (int i = 0; i < jsonObject.size(); i++) {
            jsonObject.put(arr[i].toString(), "${" + arr[i] + "}");
            System.out.println(arr[i]+"    ${"+arr[i]+"}");
        }
        String jsonStr = JSONObject.toJSONString(jsonObject);


        //转化为string字符串，遍历数组，匹配keyset中所有的值，将类似"${..}"变为${..}
        for (int i = 0; i < jsonObject.size(); i++) {

            String oldChar = "\"${" + arr[i] + "}\"";

            jsonStr = jsonStr.replace(oldChar, "${" + arr[i] + "}");

        }
        System.out.println(jsonStr);
        return jsonStr;
    }

    public static void main(String args[]) {

        String s =toMyString(
                "{\n" +
                        "\t\"import_catalog_id\": \"6572290001890058240\",\n" +
                        "\t\"coupon_ids\": [\"6674220954253660160\"],\n" +
                        "\t\"app_id\": \"6572290001458044928\"\n" +
                        "}"
        );

        System.out.println(s);




    }
}

