package com.oycl.demo.common.async2;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class BlockingTask extends Task {



     /**
     * 等待处理的Consumer.
     */
    private ConcurrentLinkedQueue<Consumer<Object>> cs = new ConcurrentLinkedQueue<>();
    /**
     * 拥有线程的个数
     */
    private int tc = 50;

    /**
     * cs的size
     */
    private AtomicInteger size = new AtomicInteger(0);


    /**
     * 默认为阻塞线程
     */
    public BlockingTask() {
        super(TaskType.BLOCKING);
    }


    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    private  CountDownLatch latch = new CountDownLatch(1);


    /**
     * 添加任务.
     */
    public void push(Consumer<Object> c) {
        try {
            this.cs.offer(c);
            this.size.incrementAndGet();
        }finally {
            //通知线程消费信息
            latch.countDown();

        }
    }



    /**
     * 队列尺寸.
     */
    public int size() {
        return this.size.get();
    }



    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc < 1 ? 1 : tc;
    }

    /**
     * 启动线程
     */
    protected void start() {
        final BlockingTask blockingTask = this;
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("service-pool-%d")
                .build();

        //创建线程池
        ExecutorService ex = new ThreadPoolExecutor(0, this.tc,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < tc; i++) {
            ex.execute(() -> {
                while (true) {
                    blockingTask.run();
                }
            });
        }
    }

    /**
     * 抢占式消费任务
     */
    private void run() {
        try {
            Consumer<Object> c;

            while ((c = this.cs.poll()) == null) {
                latch.await();
            }
            this.size.decrementAndGet();

            Misc.exeConsumer(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            synchronized (this.getLatch()){
                while(this.size() == 0 && this.getLatch().getCount() == 0){
                    //重置门闩
                    this.setLatch(new CountDownLatch(1));
                }
            }
        }

    }

    /**
     * 任务消费
     */
    public void future(Consumer<Object> c) {
        //阻塞
        if (this.type.ordinal() == TaskType.BLOCKING.ordinal()) {
            this.push(c);
            return;
        } else {
            Misc.exeConsumer(c);
        }
    }
}
