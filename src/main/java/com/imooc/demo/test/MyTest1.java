package com.imooc.demo.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MyTest1 {

    public static void main(String[] args){
//        File file = new File("src\\main\\java\\com\\imooc\\demo\\test\\test");
//        File dir = new File("src\\main\\java\\com\\imooc\\demo\\test");
//        System.out.println("file是否存在:" + file.exists());
//        System.out.println("file的完全名称："+file.getAbsoluteFile());
//        System.out.println("file是否可执行："+file.canExecute());
//        System.out.println("file是否可读："+file.canRead());
//        System.out.println("file是否可写："+file.canWrite());
//        System.out.println("file的绝对路径："+file.getAbsolutePath());
//        try {
//            System.out.println("file的绝对路径："+file.getCanonicalFile());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(new Date(file.lastModified()));
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("test.txt");
            printWriter.println("1.我要你");
            printWriter.println("2.怎么说");
            printWriter.println("3.怎么说");
            printWriter.println("4.你才能");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            printWriter.close();
        }

//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(file);
//            while(scanner.hasNext()){
//                System.out.println(scanner.next());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            scanner.close();
//        }


    }

}
