package cn.dp.ifelse.factory;

import cn.dp.ifelse.enumeration.RoleOperation;

/**
 * Created by leslie on 2019/12/29.
 */
public class RootAdminRole implements RoleOperation {

    private String roleName;

    public RootAdminRole(String roleName){
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName + " has AAA permission";
    }
}
