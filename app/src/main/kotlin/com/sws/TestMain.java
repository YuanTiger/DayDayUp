package com.sws;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * @author mengyuan
 * @date 2022/8/17/11:48 上午
 * @e-mail mengyuanzz@126.com
 * -----------
 */
public class TestMain {

    public static void main(String[] args) {

        io1();
//        readFileAndPrint();
    }

    /**
     * 文件写入流
     * FileOutputStream:文件路径的文件不存在，会自动创建
     * Java7新特性：FileOutputStream对象的创建，写在try()中，会自动关闭、自动flush()，无需在finally中手动关闭
     * 这种方式，类似于在文件上，接入了一根管子，通过这根管子，去操作文件，而不是直接操作文件本身
     */
    private static void io1() {
        try (FileOutputStream outputStream = new FileOutputStream("./io/text.txt")) {
            outputStream.write('a');
            outputStream.write('b');

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FileInputStream：文件读取流
     * 仅支持一个字节一个字节的读取，因为碰巧我们写入的是a、b两个字符，所以可以通过强转为字符将其读取出来
     * 这种方式，类似于在文件上，接入了一根管子，通过这根管子，去操作文件，而不是直接操作文件本身
     */
    private static void io2() {
        try (FileInputStream inputStream = new FileInputStream("./io/text.txt")) {
            System.out.println((char) inputStream.read());
            System.out.println((char) inputStream.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Buffer使用，使批量读取效率更高，可以减少与文件本身的交互
     */
    private static void io3() {
        try (FileInputStream inputStream = new FileInputStream("./io/text.txt")) {
            //套一层，实现字符读取
            Reader reader = new InputStreamReader(inputStream);
//            System.out.println(reader.read());
//            System.out.println(reader.read());
            //再套一层
            BufferedReader bufferedReader = new BufferedReader(reader);
            //实现一行一行读取
            bufferedReader.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件复制
     */
    private static void fileCopy() {
        try (
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("./io/my.txt"));
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("./io/new_my.txt"));
        ) {
            byte[] bytes = new byte[1024];
            int read;
            while ((read = inputStream.read(bytes)) >= 0) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * okio读取
     */
    private static void okioRead() {
        try (Source source = Okio.source(new File("./okio/my.txt"))) {
            Buffer buffer = new Buffer();
            long read = source.read(buffer, 1024);
            System.out.println(buffer.readUtf8Line());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * okio复制
     */
    private static void okioCopy() {
        try (
                BufferedSource bufferSource = Okio.buffer(Okio.source(new File("./okio/my.txt")));
                BufferedSink bufferSink = Okio.buffer(Okio.sink(new File("./okio/new_my.txt")))
        ) {
            bufferSink.writeAll(bufferSource);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = testGeneric(1);
    }

    private static <E> E  testGeneric(E name){
//        ArrayList<? super String> list = new ArrayList<Object>();
//        list.add(Integer.valueOf(1));
//        list.add("123123");
//        String a = list.get(0);
        return name;
    }


    private static <E extends Runnable & Serializable> void someMethod(E params){

    }

    private static <P> void merge(P item1, List<P> item2){

    }

}
