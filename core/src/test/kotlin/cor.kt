import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

/**
 * @Author: zhengye.zhang
 * @Description:
 * @Date: 2018/5/21 上午9:54
 */
fun main(args: Array<String>) {
}

fun suspendPrint() {
    repeat(100_000) {
        // 启动十万个协程试试
        launch {
            delay(1000)
            println(".")
        }
    }
    Thread.sleep(1200) // 等待协程代码的结束
}

/**
 * Essentially, coroutines are light-weight threads.
 *
 * They are launched with launch coroutine builder.
 */
fun cor1() {
    println("before launch: ${System.currentTimeMillis()}")
    launch {
        // launch new coroutine in background and continue
        println("start coruntine: ${System.currentTimeMillis()}")
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        println("end coruntine: ${System.currentTimeMillis()}")
    }
    println("end launch: ${System.currentTimeMillis()}")
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}

/**
 * The first example mixes non-blocking delay(...) and blocking Thread.sleep(...) in the same code.
 *
 * It is easy to get lost which one is blocking and which one is not.
 *
 * Let's be explicit about blocking using runBlocking coroutine builder:
 */
fun cor2() {
    launch {
        // launch new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main thread continues here immediately
    runBlocking {
        // but this expression blocks the main thread
        delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
    }
    /**
     * the result is the same, but this code uses only non-blocking delay.
     *
     * The main thread, that invokes runBlocking, blocks until the coroutine inside runBlocking completes.
     */
}

/**
 * This example can be also rewritten in a more idiomatic way,
 *
 * using runBlocking to wrap the execution of the main function:
 *
 * Here runBlocking<Unit> { ... } works as an adaptor that is used to start the top-level main coroutine.
 */
fun cor3() = runBlocking {
    launch {
        // launch new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues here immediately
    delay(2000L)      // delaying for 2 seconds to keep JVM alive
}

/**
 * Delaying for a time while another coroutine is working is not a good approach.
 *
 * Let's explicitly wait (in a non-blocking way) until the background Job that we have launched is complete:
 */
fun cor4() = runBlocking<Unit> {
    val job = launch {
        // launch new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // wait until child coroutine completes

}

/**
 * It launches 100K coroutines and, after a second, each coroutine prints a dot.
 *
 * Now, try that with threads. What would happen?
 *
 * (Most likely your code will produce some sort of out-of-memory error)
 */
fun cor5() = runBlocking {
    val jobs = List(100_000) {
        // launch a lot of coroutines and list their jobs
        launch {
            delay(1000L)
            print(".")
        }
    }
    jobs.forEach { it.join() } // wait for all jobs to complete
}