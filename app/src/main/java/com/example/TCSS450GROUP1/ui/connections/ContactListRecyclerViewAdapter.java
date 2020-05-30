package com.example.TCSS450GROUP1.ui.connections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentChatlistCardBinding;
import com.example.TCSS450GROUP1.databinding.FragmentContactlistCardBinding;
import com.example.TCSS450GROUP1.ui.chat.ChatRoom;

import org.json.JSONException;

import java.util.List;

public class ContactListRecyclerViewAdapter extends RecyclerView.Adapter<ContactListRecyclerViewAdapter.ContactListViewHolder> {
    List<Contacts> mContacts;

    public ContactListRecyclerViewAdapter(List<Contacts> contacts) {
        this.mContacts=contacts;
    }
    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        return new ContactListViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_contactlist_card, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder holder, int position) {
            holder.setContact(mContacts.get(position));



    }
    @Override
    public int getItemCount() {
        return mContacts.size();

    }
    public class ContactListViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public FragmentContactlistCardBinding binding;
        public ContactListViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentContactlistCardBinding.bind(view);

        }
        void setContact(final Contacts contact) {

            binding.contactlistTextView.setText(""+ contact.getUserName());

        }
    }
}
