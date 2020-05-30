package com.example.TCSS450GROUP1.ui.home;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.TCSS450GROUP1.ui.chat.ChatMessage;

public class HomeRecentViewModel extends ViewModel {

    private MutableLiveData<ChatMessage> mChat;
    private ChatMessage mMessage;
    private String mEmail;
    public HomeRecentViewModel() {

       mChat = new MediatorLiveData<>();


    }


    public void addChatObserver(@NonNull LifecycleOwner owner,
                                @NonNull Observer<? super ChatMessage> observer) {
        mChat.observe(owner, observer);
    }

    public void setEmail(String email) {
        mEmail = email;
    }
    public void changeRecentChat(ChatMessage message) {
        if(!message.equals(mChat.getValue())) {
            mChat.setValue(message);
        }
    }
    public ChatMessage getRecentChat() {
        return mMessage;
    }
}
