package cn.dp.ifelse.strategy;

import cn.dp.ifelse.enumeration.RoleOperation;

/**
 * <pre>
 *     策略上下文.
 * </pre>
 * 
 * Created by leslie on 2019/12/29.
 */
public class RoleContext {

    // 可更换的策略，传入不同的策略对象，业务即相应变化
    private RoleOperation operation;

    public RoleContext(RoleOperation operation){
        this.operation = operation;
    }

    public String execute() {
        return operation.op();
    }
}
