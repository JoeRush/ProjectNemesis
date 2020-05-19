package com.example.TCSS450GROUP1.ui.connections;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ConnectionItem implements Serializable {

    private final String mUserName;


//    private final String mFirstName;
//    private final String mLastName;
//    private final String mEmail;



    public ConnectionItem(String userName) {

        this.mUserName = userName;



    }


    /**
     * Static factory method to turn a properly formatted JSON String into a
     * ChatMessage object.
   //  * @param cmAsJson the String to be parsed into a ChatMessage Object.
     * @return a ChatMessage Object with the details contained in the JSON String.
     * @throws JSONException when cmAsString cannot be parsed into a ChatMessage.
     */
    public static ConnectionItem createFromJsonString(final String cmAsJson) throws JSONException {
        final JSONObject msg = new JSONObject(cmAsJson);
        return new ConnectionItem(msg.getString("username"));
//                msg.getString("firstname"),
//                msg.getString("lastname"),
//                msg.getString("email"));
    }

    public String getUserName() {
        return mUserName;
    }

}





