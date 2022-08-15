package com.sws.study.thread.activity

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.sws.study.R
import java.lang.Thread.sleep
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

/**
 * @author mengyuan
 * @date 2022/8/9/2:32 下午
 * @e-mail mengyuanzz@126.com
 * -----------
 * 线程基础
 */
class ThreadBaseActivity : ComponentActivity() {

    companion object {
        private const val TAG = "ThreadBaseActivity_"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_base)

//        threadStudy()
//        runnable()
//        threadFactory()
//        executor()
//        callable()
        testThreadInterrupt()
    }

    private fun testThreadInterrupt() {
        val thread = Thread {
            for (index in 1..1_000_000) {
                    if(Thread.interrupted()){
                        Log.i(TAG,"interrupted:$index")
                        return@Thread
                    }
                Log.i(TAG, "index:$index")
            }
        }
        thread.start()
        sleep(1000)
        //Android4.1.3之后，thread不能使用stop，可以查看stop源码
//        thread.stop()
        thread.interrupt()
    }


    private fun threadStudy() {
        //还原Java代码
        val thread = Thread(object : Runnable {

            override fun run() {
                Log.i(TAG, "thread start")
            }
        })
        thread.start()
    }


    /**
     * 相比threadStudy()
     * 该方法的优势在于runnable可以复用
     * 不过一般正式写程序不使用，管理性太低
     */
    private fun runnable() {
        val runnable = Runnable {
            Log.i(TAG, "runnable start")

        }

        val thread = Thread(runnable)
        thread.start()
    }


    /**
     * 线程池，利用典型的工厂模式
     */
    private fun threadFactory() {
        val threadFactory = MyThreadFactory()

        val runnable1 = Runnable {
            Log.i(TAG, "threadFactory runnable 111 start")
        }
        val runnable2 = Runnable {
            Log.i(TAG, "threadFactory runnable 222 start")
        }
        val thread1 = threadFactory.newThread(runnable1)
        val thread2 = threadFactory.newThread(runnable2)


        thread1.start()
        thread2.start()
    }


    private fun executor() {
        val runnable = Runnable {
            Log.i(TAG, "executor  start")
        }
        val newCachedThreadPool = Executors.newCachedThreadPool()
//        val newSingleThreadExecutor = Executors.newSingleThreadExecutor()
//        Executors.newFixedThreadPool(10)
        newCachedThreadPool.execute(runnable)
        newCachedThreadPool.execute(runnable)
        newCachedThreadPool.execute(runnable)

        /**
         * corePoolSize：默认的线程数，这些线程是不会被销毁的，线程池初始化时就会存在
         * maximumPoolSize：最大的线程数量，线程数量不会超出该数量，任务太多线程不够时，会进入等待
         * keepAliveTime：当线程闲置时，闲置多久后会被回收
         * unit：时间单位
         */
        val executeService = ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            SynchronousQueue<Runnable>()
        )
    }


    private fun callable() {
        val callable = MyCallable()
        val newCachedThreadPool = Executors.newCachedThreadPool()
        val future = newCachedThreadPool.submit(callable)
        val result = future.get()
        Log.i(TAG, "result:${result}")
    }


    inner class MyThreadFactory : ThreadFactory {
        //使用Atomic包裹对象：具有同步性+原子性
        private val count = AtomicInteger(0)

        override fun newThread(r: Runnable): Thread {
            // count.incrementAndGet():++count
            return Thread(r, "ThreadName-" + count.incrementAndGet())
        }
    }

    inner class MyCallable : Callable<String> {

        override fun call(): String {
            Thread.sleep(1000 * 2)
            return "Done"
        }

    }

}