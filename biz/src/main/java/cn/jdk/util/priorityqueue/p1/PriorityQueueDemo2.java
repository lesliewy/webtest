package cn.jdk.util.priorityqueue.p1;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by leslie on 2019/11/23.
 */
public class PriorityQueueDemo2 {

    public static void main(String[] args) {
        // 通过改造器指定排序规则
        PriorityQueue<Student> q = new PriorityQueue<Student>(new Comparator<Student>() {

            @Override
            public int compare(Student o1, Student o2) {
                // 按照分数低到高，分数相等按名字
                if (o1.getScore() == o2.getScore()) {
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getScore() - o2.getScore();
            }
        });
        // 入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        // 出列
        System.out.println(q.poll()); // Student{name='will', score=17}
        System.out.println(q.poll()); // Student{name='bunny', score=20}
        System.out.println(q.poll()); // Student{name='dafei', score=20}
        System.out.println(q.poll()); // Student{name='setf', score=30}
    }
}

class Student {

    private String name;  // 名字
    private int    score; // 分数

    public Student(String name, int s){
        this.name = name;
        this.score = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "name=" + name + "; score=" + score;
    }
}
