package forTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * todo
 * 当开发维护接口时，肯定有所变动，我将jmeter原本请求中的的{"a":${}}与现接口做判断，看出哪些多了哪些少了
 */
public class compareTwo {
    public static void main(String args[]) {

        String oldString = "{\"collect_amount_type\":\"0\",\"activity_no\":6655817150663626752  ,\"allow_gift\":true,\"end_time\":\"2020-04-14\",\"available_time_type\":1,\"exchange_mode\":0,\"stakeholder_undertake_list\": [{ \t\t\"stakeholder_id\": 6654182780865482752, \t\t\"stakeholder_undertake_amount\": 3000 \t}, \t{ \t\t\"stakeholder_id\": 6654182561104924672, \t\t\"stakeholder_undertake_amount\": 2000 \t}],\"undertake_type\":\"percent\",\"per_user_limit_month\":0,\"mp_undertake_amount\":4000,\"start_time\":\"2020-04-14\",\"available_time_info\":{ \t\t\"valid_time\": \"\", \t\t\"week\": [], \t\t\"time\": [] \t},\"per_user_limit\":0,\"target_app\":[{ \t\t\"app_id\": \"6575263228287393792\", \t\t\"app_name\": \"商户0905001\" \t}],\"per_user_limit_day\":0,\"name\":\"mj10-9_109\",\"merchant_percent\":0.1,\"init_count\":100,\"target_group\":[],\"template_no\":6655817149711519744,\"app_id\":6572290001458044928,\"per_user_limit_week\":0}";
        String newString = "{\"app_id\":\"6572290001458044928\",\"template_no\":\"6655760758468644864\",\"activity_no\":\"6655817150663626752\",\"start_time\":\"2020-04-14\",\"end_time\":\"2020-04-14\",\"available_time_type\":\"1\",\"available_time_info\":{\"valid_time\":\"\",\"week\":[],\"time\":[]},\"merchant_percent\":0.1,\"per_user_limit\":0,\"init_count\":100,\"per_user_limit_day\":0,\"per_user_limit_week\":0,\"per_user_limit_month\":0,\"target_app\":[{\"app_id\":\"6575263228287393792\",\"app_name\":\"商户0905001\"}],\"target_group\":[],\"exchange_mode\":\"0\",\"name\":\"满10减9.99券模板\",\"mp_undertake_amount\":4000,\"undertake_type\":\"percent\",\"allow_gift\":false,\"stakeholder_undertake_list\":[{\"stakeholder_undertake_amount\":3000,\"stakeholder_id\":\"6654182780865482752\"},{\"stakeholder_undertake_amount\":2000,\"stakeholder_id\":\"6654182561104924672\"}],\"collect_amount_type\":\"0\"}"
               ;

        JSONObject oldJSON = JSONObject.parseObject(oldString);
        JSONObject newJSON = JSONObject.parseObject(newString);
        Object oldKey[] = oldJSON.keySet().toArray();
        Object newKey[] = newJSON.keySet().toArray();

        System.out.println("删除的key");
        for (int i = 0; i < oldJSON.size(); i++) {

            if (!newJSON.containsKey(oldKey[i]) && oldKey[i] != null) {

                System.out.println(oldKey[i]);

            }

        }

        System.out.println("新增的key");
        for (int j = 0; j < newJSON.size(); j++) {

            if (!oldJSON.containsKey(newKey[j]) && newKey[j] != null) {

                System.out.println(newKey[j]);

            }

        }
        System.out.println("__________");

        for (int j = 0; j < newJSON.size(); j++) {

            if (oldJSON.containsKey(newKey[j]) && newKey[j] != null) {

                System.out.println(newKey[j]+"    "+oldJSON.getString(newKey[j].toString()));

            }

        }



    }
}




