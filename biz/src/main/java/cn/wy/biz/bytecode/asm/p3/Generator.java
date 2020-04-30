package cn.wy.biz.bytecode.asm.p3;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * <pre>
 *     先执行Generator.main(), 再执行Test, 看是否增强.
 *     可以利用 asm bytecode outline 这个idea plugin，方便的使用asm 修改指令：
 *     1，先写好目标代码，例如需要在进入方法的第一行: System.out.println("start");  在方法的最后一行: System.out.println("end")
 *     2, 右键 -> show bytecode outline.  找到这两行代码对应的asm core api 的写法
 *     3, 将目标代码的asm写法放入MethodAdapter 中.
 *
 * </pre>
 * Created by leslie on 2020/4/29.
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        // 读取
        ClassReader classReader = new ClassReader("cn.wy.biz.bytecode.asm.p3.Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        // 处理
        ClassAdapter classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        // 输出

        File f = new File(System.getProperty("user.dir") + "/biz/target/classes/cn/wy/biz/bytecode/asm/p3/Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");
    }
}
