package com.imooc.demo;

import com.imooc.demo.dto.UserDto;

import java.io.*;

public class FileStreamTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
//        FileOutputStream fileOutputStream = new FileOutputStream("test.dat");
//        for(int i = 0; i < 10; i++) {
//            fileOutputStream.write(i);
//        }
//        fileOutputStream.close();

//        FileInputStream fileInputStream = new FileInputStream("test.dat");
//        int temp;
//        while ((temp = fileInputStream.read()) != -1) {
//            System.out.println(temp);
//        }
//        fileInputStream.close();

//        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("test.dat"));
//        dataOutputStream.writeUTF("肖彤，我喜欢你");
//        dataOutputStream.writeInt(5201314);
//        dataOutputStream.close();
//
//        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("test.dat"));
//        System.out.println(dataInputStream.readUTF());
//        System.out.println(dataInputStream.readInt());
//        dataInputStream.close();
//
//        BufferedInputStream input = new BufferedInputStream(new FileInputStream("test.dat"));
//        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("test2.dat"));
//        int temp;
//        int fileSize = 0;
//        while((temp = input.read()) != -1){
//            output.write(temp);
//            fileSize++;
//        }
//        System.out.println("文件大小" + fileSize);
//        output.close();
//        input.close();

//        UserDto userDto = new UserDto();
//        userDto.setId("001");
//        userDto.setLoginName("时戚戚");
//        userDto.setName("肖彤");
//        userDto.setPassword("5201314");
//        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("object.dat")));
//        outputStream.writeObject(userDto);
//        outputStream.close();
//
//        ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("object.dat")));
//        System.out.println(inputStream.readObject());
//
//        inputStream.close();

        UserDto userDto1 = new UserDto();
        userDto1.setId("001");
        userDto1.setLoginName("时戚戚");
        userDto1.setName("肖彤");
        userDto1.setPassword("5201314");

        UserDto userDto2 = new UserDto();
        userDto2.setId("002");
        userDto2.setLoginName("星空的未来");
        userDto2.setName("刘力健");
        userDto2.setPassword("52013142");

        userDto1.setLoveObject(userDto2);

        UserDto userDto1Clone = (UserDto) userDto1.clone();
        UserDto userDto1DeepClone = deepClone(userDto1);

        System.out.println(userDto1);
        System.out.println(userDto1Clone);
        System.out.println(userDto1DeepClone);

        userDto2.setLoginName("未来的星空");

        System.out.println(userDto1);
        System.out.println(userDto1Clone);
        System.out.println(userDto1DeepClone);

    }

    private static <T> T deepClone(T object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (T) objectInputStream.readObject();
    }

}
