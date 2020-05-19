package com.example.TCSS450GROUP1.ui.connections;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentConnectionViewBinding;

import java.util.List;

public class ContactRecyclerViewAdapter extends
        RecyclerView.Adapter<ContactRecyclerViewAdapter.ContactViewHolder> {

    private final List<ConnectionItem> mContacts;

    public ContactRecyclerViewAdapter(List<ConnectionItem> items) {
        this.mContacts = items;
    }

    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_connection_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.setContact(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public FragmentConnectionViewBinding binding;

        public ContactViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentConnectionViewBinding.bind(view);

            //may need to change
            view.setOnClickListener(v -> {
                navigateToChat();
            });
        }
        private void navigateToChat(){}
        void setContact(final ConnectionItem contact) {
            binding.textContactUsername.setText(contact.getUserName());
        }
    }

}

