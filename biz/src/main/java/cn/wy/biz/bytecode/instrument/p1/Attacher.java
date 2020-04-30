package cn.wy.biz.bytecode.instrument.p1;

import java.io.IOException;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

/**
 * <pre>
 *     使用agent 在运行时修改bytecode:
 *     1, jar cvfm wyagent.jar manifest.txt /Users/leslie/MyProjects/GitHub/webtest/biz/target/classes/cn/wy/biz/bytecode/instrument/p1/TestAgent.class
 *        打包agent.
 *     2, 执行这里的Base.main(), 并查看pid
 *     3, 修改Attacher 中的pid为 运行Base的java进程的pid.
 *     4, 观察Base.main()的输出.
 * </pre>
 * 
 * Created by leslie on 2020/4/30.
 */
public class Attacher {

    public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException,
                                           AgentInitializationException {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("99325");
        vm.loadAgent("/Users/leslie/MyProjects/GitHub/webtest/biz/src/main/java/cn/wy/biz/bytecode/instrument/p1/wyagent.jar");
    }
}
