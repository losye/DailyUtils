import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Author: zhengye.zhang
 * @Description:
 * @Date: 2018/5/21 上午9:54
 */
fun main(args: Array<String>) {
    /*launch {
        val async1 = async { requestDataAsync1() }
        val async2 = async { requestDataAsync2() }
        println("data1=${async1.await()}, data2=${async2.await()}")
    }
    Thread.sleep(10000)*/

    cor6()
}

suspend fun requestDataAsync1(): String {
    delay(1000)
    return "data1"
}

suspend fun requestDataAsync2(): String {
    delay(1000)
    return "data2"
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

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

/**
 * Conceptually, async is just like launch.
 * It starts a separate coroutine which is a light-weight thread that works concurrently
 * with all the other coroutines. The difference is that launch returns a Job
 * and does not carry any resulting value, while async returns a Deferred --
 * a light-weight non-blocking future that represents a promise to provide a result later.
 * You can use .await() on a deferred value to get its eventual result,
 * but Deferred is also a Job, so you can cancel it if needed.
 */
fun cor6() = runBlocking<Unit> {
    val time = measureTimeMillis {
        //sequentially
        /*val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")*/

        //async
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }

        val launch = launch { doSomethingUsefulOne() }

        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}


