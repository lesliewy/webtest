package cn.dp.ifelse.factory;

/**
 * <pre>
 *     工厂模式
 * </pre>
 * Created by leslie on 2019/12/29.
 */
public class JudgeRole {

    public String judge(String roleName) {
        // 一行代码搞定！之前的 if/else也没了！
        return RoleFactory.getOp(roleName).op();
    }
}
