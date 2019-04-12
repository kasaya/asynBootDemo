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
     * 线程忙？
     */
    public volatile boolean busy = false;

    public BlockingTask() {
        super(TaskType.BLOCKING);
    }

    /**
     * 添加任务.
     */
    public void push(Consumer<Object> c) {
        this.cs.add(c);
        this.size.incrementAndGet();
        //通知线程消费信息
        synchronized (this) {
            this.notify();
        }
    }

    /**
     * 线程忙?
     */
    public boolean isBusy() {
        return this.busy;
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
        BlockingTask ab = this;
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
                    ab.run();
                }
            });
        }
    }

    /**
     * 抢占式消费任务
     */
    private void run() {
        Consumer<Object> c = this.cs.poll();
        if (c == null) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                }
            }
            c = this.cs.poll();
        }
        if (c != null) /* 抢占式. */ {
            this.size.decrementAndGet();
            this.busy = true;
            Misc.exeConsumer(c);
            this.busy = false;
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
