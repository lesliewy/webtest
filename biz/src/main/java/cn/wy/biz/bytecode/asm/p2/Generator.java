package cn.wy.biz.bytecode.asm.p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * <pre>
 *    先执行 Generator.main() 来增强C.class, 再执行Test来测试是否以增强.
 *
 *    Created by leslie on 2020/4/29.
 * </pre>
 *
 */
public class Generator {

    public static void main(String[] args) {
        try {
            ClassReader cr = new ClassReader("cn.wy.biz.bytecode.asm.p1.C");
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddTimeClassAdapter(cw);
            // 使给定的访问者访问Java类的ClassReader
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            File file = new File(System.getProperty("user.dir")
                                 + "/biz/target/classes/cn/wy/biz/bytecode/asm/p1/C.class");
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(data);
            fout.close();
            System.out.println("success!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
