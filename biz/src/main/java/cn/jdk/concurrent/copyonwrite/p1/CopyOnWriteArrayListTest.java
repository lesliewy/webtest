package cn.jdk.concurrent.copyonwrite.p1;

/**
 * <pre>
 *   写时复制. 此做法主要的优点是如果调用者没有修改资源，就不会有副本（private copy）被创建，因此多个调用者只是读取操作时可以共享同一份资源.
 *   写时复制是通过加锁实现的, 否则线程很多时会复制多份拷贝。add(), set(), remove() 时使用ReentrantLock.
 *   读的时候不需要加锁，而vector 是所有操作都加锁, 即使是get()，实现的是强一致性。
 *
 *   应用:
 *      用于读多写少的并发场景.  比如白名单，黑名单，商品类目的访问和更新场景.
 *
 *   缺点:
 *      内存空间
 *      只保证最终一致性。
 * </pre>
 * 
 * Created by leslie on 2019/12/17.
 */
public class CopyOnWriteArrayListTest {

}
