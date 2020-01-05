package cn.dp.ifelse.factory;

import java.util.HashMap;
import java.util.Map;

import cn.dp.ifelse.enumeration.RoleOperation;

/**
 * Created by leslie on 2019/12/29.
 */
public class RoleFactory {

    static Map<String, RoleOperation> roleOperationMap = new HashMap<>();

    // 在静态块中先把初始化工作全部做完
    static {
        roleOperationMap.put("ROLE_ROOT_ADMIN", new RootAdminRole("ROLE_ROOT_ADMIN"));
        roleOperationMap.put("ROLE_ORDER_ADMIN", new OrderAdminRole("ROLE_ORDER_ADMIN"));
        roleOperationMap.put("ROLE_NORMAL", new NormalRole("ROLE_NORMAL"));
    }

    public static RoleOperation getOp(String roleName) {
        return roleOperationMap.get(roleName);
    }
}
