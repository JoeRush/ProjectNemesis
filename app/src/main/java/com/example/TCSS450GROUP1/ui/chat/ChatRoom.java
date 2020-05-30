package com.example.TCSS450GROUP1.ui.chat;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Jaskaran Mangat
 */
public class ChatRoom implements Serializable {

    private final String email;
    private final int rowCount;


    public ChatRoom(JSONObject json, int count) throws Exception {

        rowCount = count;
        email = json.getString("email");
    }

    /**
     * Returns row count.
     * @return Amount of rows.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Returns the email.
     * @return String of email.
     */
    public String getEmail() {
        return email;
    }

}


