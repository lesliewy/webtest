package cn.dp.ifelse.factory;

import cn.dp.ifelse.enumeration.RoleOperation;

/**
 * Created by leslie on 2019/12/29.
 */
public class NormalRole implements RoleOperation {

    private String roleName;

    public NormalRole(String roleName){
        this.roleName = roleName;
    }

    @Override
    public String op() {
        return roleName + " has CCC permission";
    }
}
