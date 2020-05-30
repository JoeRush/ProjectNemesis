package com.example.TCSS450GROUP1.ui.connections;

import org.json.JSONObject;

import java.io.Serializable;

public class Contacts implements Serializable {


        private final String mUserName;

        /**



        /**
         * Constructor that instantiates fields from a JSONObject.
         */
        public Contacts(String username, int rowCount) throws Exception {

            mUserName = username;

        }


        /**
         * Returns username
         * @return
         */
        public String getUserName() {
            return mUserName;
        }






}


