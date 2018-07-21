package classloader;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @Author: zhengye.zhang
 * @Description:
 * @Date: 2018/7/21 下午1:16
 */
public class Server {
    public static void main(String[] args) {
        final String classPath = "/Users/zhengye.zhang/github/DailyUtils/core/";
        final String className = "src.main.java.classloader.Foo";
        final String fileName = className.replace(".", "/") + ".class";

        File f = new File(classPath, fileName);
        ClassFileObserver cfo = new ClassFileObserver(f.getAbsolutePath());

        cfo.addObserver((o, arg) -> {
            try {

                Object[] loadTimes = (Object[]) arg;
                System.out.println(loadTimes[0] + " <---> " + loadTimes[1]);// 新旧时间对比

                Class<?> loadClass = HotClassLoader.get(classPath)
                        .loadClass(className);
                Object person = loadClass.newInstance();
                Method sayHelloMethod = loadClass.getMethod("bar");
                sayHelloMethod.invoke(person);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        cfo.startObserve();
    }
}
