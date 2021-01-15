package cn.colinal.homework_01;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class H1Main {
    static String rootStr = H1Main.class.getResource("/").getPath();
    static File newFile = new File(rootStr+"/homework_01/Hello.class");

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        InputStream is = new FileInputStream(new File(rootStr+"/homework_01/Hello.xlass"));
        byte[] a = new byte[1];

        if (newFile.exists()){
            newFile.delete();
        }
        newFile.createNewFile();
        OutputStream os = new FileOutputStream(newFile);
        while (is.read(a)!=-1){
            byte temp = (byte) (255 - a[0]);
            os.write(temp);
        }
        os.flush();
        MyClassLoader mcl = new MyClassLoader();
        Class<?> hello = mcl.findClass("Hello");
        Object o = hello.newInstance();
        Method helloMethod = hello.getMethod("hello");
        helloMethod.invoke(o);
    }


    private static class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] cLassBytes = null;
            try {
                cLassBytes = Files.readAllBytes(Paths.get(newFile.getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Class clazz = defineClass(name, cLassBytes, 0, cLassBytes.length);
            return clazz;
        }
    }
}

