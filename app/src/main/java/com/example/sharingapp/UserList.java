package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by rosacristobalcastro on 23/11/17.
 */

//package com.example.sharingapp;

            import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
            import com.google.gson.reflect.TypeToken;

            import java.io.FileInputStream;
            import java.io.FileNotFoundException;
            import java.io.FileOutputStream;
            import java.io.IOException;
            import java.io.InputStreamReader;
            import java.io.OutputStreamWriter;
            import java.lang.reflect.Type;
            import java.util.ArrayList;

/**
 * UserList class
 */
public class UserList {

    private static ArrayList<User> users;
    private String FILENAME = "user_file.sav";

    public UserList() {
        users = new ArrayList<User>();
    }

    public void setUsers(ArrayList<User> user_list) {
        users = user_list;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<String> getAllUsernames() {
        ArrayList<String> usernames = new ArrayList<>(users.size());
        for (User i : users) {
            usernames.add(i.getUsername());
        }
        return usernames;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public boolean hasUser(User user) {
        for (User i : users) {
            if (user.getId().equals(i.getId())) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(User user) {
        int pos = 0;
        for (User i : users) {
            if (user.getId().equals(i.getId())) {
                return pos;
            }
            pos = pos+1;
        }
        return -1;
    }

    public int getSize() {
        return users.size();
    }

    public void loadUsers(Context context) {

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            users = new ArrayList<User>();
        } catch (IOException e) {
            users = new ArrayList<User>();
        }
    }

    public void saveUsers(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(users, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        for (User i : users) {
            if (i.getUsername().equals(username)) {
                return i;
            }
        }
        return null;
    }

    public boolean isUsernameAvailable(String username) {
        for (User i : users) {
            if (i.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}


