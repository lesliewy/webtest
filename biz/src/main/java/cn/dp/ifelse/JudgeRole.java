package cn.dp.ifelse;

/**
 * <pre>
 *     传统做法;
 *     switch case 与此相同，设计上并不合适.
 *
 *     扩展受限；
 *     不符合开闭原则;
 * </pre>
 * 
 * Created by leslie on 2019/12/29.
 */
public class JudgeRole {

    public String judge(String roleName) {
        String result = "";
        if (roleName.equals("ROLE_ROOT_ADMIN")) {
            // 系统管理员有A权限
            result = "ROLE_ROOT_ADMIN: " + "has AAA permission";
        } else if (roleName.equals("ROLE_ORDER_ADMIN")) {
            // 订单管理员有B权限
            result = "ROLE_ORDER_ADMIN: " + "has BBB permission";
        } else if (roleName.equals("ROLE_NORMAL")) {
            // 普通用户有C权限
            result = "ROLE_NORMAL: " + "has CCC permission";
        } else {
            result = "XXX";
        }
        return result;
    }
}
