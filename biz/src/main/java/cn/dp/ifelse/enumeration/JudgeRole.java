package cn.dp.ifelse.enumeration;

/**
 * Created by leslie on 2019/12/29.
 */
public class JudgeRole {

    public String judge(String roleName) {
        // 一行代码搞定！之前的if/else没了！
        return RoleEnum.valueOf(roleName).op();
    }
}
