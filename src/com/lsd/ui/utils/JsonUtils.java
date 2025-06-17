package com.lsd.ui.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lsd.ui.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.Type;

public class JsonUtils {
    //save
    public static void saveUsersToJson(ArrayList<User> list, String fileName) {
        try(FileWriter writer = new FileWriter(fileName)){
            Gson gson  = new Gson();
            gson.toJson(list,writer);
            System.out.println("保存成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取list
    public static ArrayList<User> loadUsersFromJson(String fileName) {
        try(FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Type userType = new TypeToken<ArrayList<User>>() {}.getType();
            return gson.fromJson(reader,userType);//把一个 JSON 文件或字符串，解析成一个 ArrayList<User> 对象
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
