package forTest;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * todo
 * 当开发维护接口时，肯定有所变动，我将jmeter原本请求中的的{"a":${}}与现接口做判断，看出哪些多了哪些少了
 */
public class compare {
    public static void main(String args[]) {

        String oldString = "{\n" +
                "\t\"app_id\": \"6572290001458044928\",\n" +
                "\t\"template_no\": \"6655754757656940544\",\n" +
                "\t\"activity_no\": \"6655760759211036672\",\n" +
                "\t\"start_time\": \"2020-04-14\",\n" +
                "\t\"end_time\": \"2020-04-14\",\n" +
                "\t\"available_time_type\": \"1\",\n" +
                "\t\"merchant_percent\": 1,\n" +
                "\t\"per_user_limit\": 0,\n" +
                "\t\"init_count\": 1,\n" +
                "\t\"per_user_limit_day\": 0,\n" +
                "\t\"per_user_limit_week\": 0,\n" +
                "\t\"per_user_limit_month\": 0,\n" +
                "\t\"target_app\": [{\n" +
                "\t\t\"app_id\": \"6575263228287393792\",\n" +
                "\t\t\"app_name\": \"商户0905001\"\n" +
                "\t}],\n" +
                "\t\"target_group\": [],\n" +
                "\t\"exchange_mode\": \"0\",\n" +
                "\t\"name\": \"满10减9.99券模板\",\n" +
                "\t\"undertake_type\": \"percent\",\n" +
                "\t\"allow_gift\": false,\n" +
                "\t\"stakeholder_undertake_list\": [],\n" +
                "\t\"collect_amount_type\": \"0\"\n" +
                "}";
        String newString = "{\n" +
                "\t\"collect_amount_type\": \"0\",\n" +
                "\t\"activity_no\": 6655760759211036672,\n" +
                "\t\"allow_gift\": true,\n" +
                "\t\"end_time\": \"2020-04-14\",\n" +
                "\t\"available_time_type\": 1,\n" +
                "\t\"exchange_mode\": 0,\n" +
                "\t\"stakeholder_undertake_list\": [],\n" +
                "\t\"undertake_type\": \"percent\",\n" +
                "\t\"per_user_limit_month\": 0,\n" +
                "\t\"mp_undertake_amount\": 4000,\n" +
                "\t\"start_time\": \"2020-04-14\",\n" +
                "\t\"per_user_limit\": 0,\n" +
                "\t\"target_app\": [{\n" +
                "\t\t\"app_id\": \"6575263228287393792\",\n" +
                "\t\t\"app_name\": \"商户0905001\"\n" +
                "\t}],\n" +
                "\t\"per_user_limit_day\": 0,\n" +
                "\t\"name\": \"mj10-9_66\",\n" +
                "\t\"merchant_percent\": 0.1,\n" +
                "\t\"init_count\": 100,\n" +
                "\t\"target_group\": [],\n" +
                "\t\"template_no\": 6655760758468644864,\n" +
                "\t\"app_id\": 6572290001458044928,\n" +
                "\t\"per_user_limit_week\": 0\n" +
                "}";



        //匹配jmeter中的"xxxx":
        Pattern p = Pattern.compile("\".*?\":");
        Matcher m = p.matcher(oldString);
        int number = 0;
        String oldKey[] = new String[40];
        //去掉" "：
        while (m.find()) {
            oldKey[number] = m.group().replace("\"", "").replace(":", "");
            number++;
        }

        //通过判断newJSON中是否包含oldJSON中的key值来判断是否被删除
        JSONObject newJSON = JSONObject.parseObject(newString);

        System.out.println("删除key值：");
        for (int i = 0; i < oldKey.length; i++) {

            if (!newJSON.containsKey(oldKey[i]) && oldKey[i] != null) {

                System.out.println(oldKey[i]);

            }

        }
        //通过判断oldString中是否包含newJSON中的key值来判断是否被删除
        Object newKey[] = newJSON.keySet().toArray();
        System.out.println("增加key值：");
        int isExist;
        int i ,j;
        for (i = 0; i < newKey.length; i++) {

            isExist = -1;
            for (j = 0; j < oldKey.length; j++) {

                if ((newKey[i].equals(oldKey[j]))) {
                    isExist = 1;

                }
            }

            if (isExist == -1) {
                System.out.println(newKey[i]);
            }


        }


    }
}




