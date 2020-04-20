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
                        "\t\"coupon_interface_no\": \"ps6001\",\n" +
                        "\t\"user_id\": \"PencYH6BXEbobm35WNT5nFyPqDT4XanOHB1H7dCkEw6bncg64tLk9e/BFihBwTHJ\",\n" +
                        "\t\"mode\": \"unionpay\",\n" +
                        "\t\"out_coupon_no\": \"10086\",\n" +
                        "\t\"out_order_no\": \"1585294679558\",\n" +
                        "\t\"user_info\": {\n" +
                        "\t\t\"phone\": \"18758580115\"\n" +
                        "\t},\n" +
                        "\t\"exchange_list\": [{\n" +
                        "\t\t\"activity_coupon_no\": \"6656022138153209856\",\n" +
                        "\t\t\"exchange_count\": 1\n" +
                        "\t}]\n" +
                        "}"
        );

        System.out.println(s);




    }
}
