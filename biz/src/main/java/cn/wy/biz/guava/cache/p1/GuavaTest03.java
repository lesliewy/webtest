package cn.wy.biz.guava.cache.p1;

import java.util.concurrent.ExecutionException;

/**
 * Created by leslie on 2020/4/13.
 */
public class GuavaTest03 {
    /*
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        LoadingCache cache = CacheBuilder.newBuilder()
                .maximumSize(CACHE_SIZE)
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, String>() {
                    public String load(Integer key) throws InterruptedException {
                        System.out.println(System.currentTimeMillis() + "--->" + Thread.currentThread().getName() + " load start for key: " + key);
                        System.out.println(System.currentTimeMillis() + "--->" + Thread.currentThread().getName() + " load end for key: " + key);
                        return "" + key;
                    }

                    @Override
                    public ListenableFuture<String> reload(Integer key, String oldValue) throws Exception {
                        ListenableFutureTask<String> task = ListenableFutureTask.create(() -> {
                            return load(key + 1);
                        });
                        executor.execute(task);
                        return task;
                    }
                });

        for (int i = 1; i < 10; i ++ ){
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(System.currentTimeMillis() + "--->" + Thread.currentThread().getName()  + ": get value: " + cache.get(1));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        Thread.sleep(4000);
        System.out.println("-------------------------------------------------");

        for (int i = 1; i < 10; i ++ ){
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(System.currentTimeMillis() + "--->" + Thread.currentThread().getName()  + ":again get value: " + cache.get(1));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }
    */
}
