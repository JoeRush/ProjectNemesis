package com.example.TCSS450GROUP1.ui.chat;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.model.UserInfoViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jaskaran Mangat
 */
public class ChatListViewModel extends AndroidViewModel {

    /**
     * Mutable Live Data of List<ChatRoom>.
     */
    private MutableLiveData<List<ChatRoom>> mChatRoomList;

    /**
     * UserInfoViewModel object.
     */
    private UserInfoViewModel userInfoViewModel;

    /**
     * ChatListViewModel constuctor that accepts an application object.
     * @param application Application object.
     */
    public ChatListViewModel(@NonNull Application application) {
        super(application);

        mChatRoomList = new MutableLiveData<>();
        mChatRoomList.setValue(new ArrayList<>());


    }

    /**
     * An observer on the HTTP Response from the web server.
     * @param owner LifecycleOwner object. 
     * @param observer Observer object of type List<ChatRoom>. 
     */
    public void addChatListObserver(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<ChatRoom>> observer) {
        mChatRoomList.observe(owner, observer);
    }

    /**
     * Method to handle volley errors. 
     * @param error VolleyError object. 
     */
    private void handleError(final VolleyError error) {
        if (error != null && error.getMessage() != null) {
            Log.e("CONNECTION ERROR", error.getMessage());
            throw new IllegalStateException(error.getMessage());
        }
    }


    /**
     * Method to interpret given JSONObject. 
     * @param result Given JSONObject object. 
     */
    private void handleResult(final JSONObject result) {

        if (!result.has("rows")) {
            throw new IllegalStateException("Unexpected response in ChatListViewModel: " + result);
        }
        try {

            ArrayList<ChatRoom> listOfEmails = new ArrayList<>();
            JSONArray rows = result.getJSONArray("rows");
            int rowCount = result.getInt("rowCount");

            for (int i = 0; i < rows.length(); i++) {
                JSONObject row = rows.getJSONObject(i);
                ChatRoom cr = new ChatRoom(row, rowCount);
                listOfEmails.add(cr);
            }
            mChatRoomList.setValue(listOfEmails);
            Log.d("JSON", "" + listOfEmails.toString());
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    /**
     * Method to connect to webservice and get chat data.
     */
    public void connectGet() {
        if (userInfoViewModel == null) {
            throw new IllegalArgumentException("No UserInfoViewModel is assigned");
        }
        String url = getApplication().getResources().getString(R.string.base_url) +
                "chats/" + "1";

        Request request = new JsonObjectRequest(Request.Method.GET, url, null,
                //no body for this get request
                this::handleResult, this::handleError) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                // add headers <key,value>
                headers.put("Authorization", "Bearer " + userInfoViewModel.getJWT());
                return headers;
            }
        };                                   
        request.setRetryPolicy(new DefaultRetryPolicy(10_000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext()).add(request);
    }

    public void setUserInfoViewModel(UserInfoViewModel vm) {
        userInfoViewModel = vm;
    }


}
