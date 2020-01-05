package cn.dp.ifelse.factory;

import cn.dp.ifelse.enumeration.RoleOperation;

/**
 * Created by leslie on 2019/12/29.
 */
public class OrderAdminRole implements RoleOperation {

    private String roleName;

    public OrderAdminRole(String roleName){
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName + " has BBB permission";
    }

}
