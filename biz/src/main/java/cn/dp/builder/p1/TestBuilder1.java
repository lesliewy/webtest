package cn.dp.builder.p1;

/**
 * Created by leslie on 2020/5/8.
 */
public class TestBuilder1 {

    public static void main(String[] args) {
        TestBuilder1 tb1 = new TestBuilder1();
        tb1.testStudent();

        tb1.testMessageDialog();
    }

    private void testStudent() {
        Student s1 = new Student().setName("wy").setAge(10).setSex("M");
        System.out.println(s1);
    }

    private void testMessageDialog() {
        // 类实例访问静态成员. 不推荐这么做.
        MessageDialog.doSth1().doSth2();

        MessageDialog.doSth3().doSth4();
    }

}
