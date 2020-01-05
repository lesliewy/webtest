package cn.dp.ifelse.strategy;

import cn.dp.ifelse.enumeration.RoleOperation;
import cn.dp.ifelse.factory.NormalRole;
import cn.dp.ifelse.factory.OrderAdminRole;
import cn.dp.ifelse.factory.RootAdminRole;

/**
 * <pre>
 *     策略模式.
 * </pre>
 * 
 * Created by leslie on 2019/12/29.
 */
public class JudgeRole {

    public String judge(RoleOperation roleOperation) {
        RoleContext roleContext = new RoleContext(roleOperation);
        return roleContext.execute();
    }

    public static void main(String[] args) {
        JudgeRole judgeRole = new JudgeRole();
        String result1 = judgeRole.judge(new RootAdminRole("ROLE_ROOT_ADMIN"));
        System.out.println(result1);
        String result2 = judgeRole.judge(new OrderAdminRole("ROLE_ORDER_ADMIN"));
        System.out.println(result2);
        String result3 = judgeRole.judge(new NormalRole("ROLE_NORMAL"));
        System.out.println(result3);
    }
}
