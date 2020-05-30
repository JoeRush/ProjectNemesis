package com.example.TCSS450GROUP1.ui.connections.contacts;

import org.json.JSONObject;

import java.io.Serializable;

public class Contacts implements Serializable {


        private final String mUserName;
        /**

        /**
         * Constructor that intantiates fields.
         * @param userName
         *
         */
        public Contacts(String userName) {


            this.mUserName = userName;

        }

        /**
         * Constructor that instantiates fields from a JSONObject.
         */
        public Contacts(JSONObject json) throws Exception {

            mUserName = json.getString("username");

        }


        /**
         * Returns username
         * @return
         */
        public String getUserName() {
            return mUserName;
        }




    }


