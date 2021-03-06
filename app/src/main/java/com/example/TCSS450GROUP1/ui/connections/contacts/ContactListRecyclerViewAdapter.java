package com.example.TCSS450GROUP1.ui.connections.contacts;

import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentContactlistCardBinding;


import java.util.List;
public class ContactListRecyclerViewAdapter extends RecyclerView.Adapter<ContactListRecyclerViewAdapter.ContactListViewHolder> {

    /**
     * List of contacts.
     */
    List<Contacts> mContacts;

    /**
     * Constructor that instantiates fields.
     * @param contacts
     */
    public ContactListRecyclerViewAdapter(List<Contacts> contacts) {
        this.mContacts = contacts;
    }

    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ContactListViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.fragment_contactlist_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder holder, int position) {

        holder.setContact(mContacts.get(position));
    }

    @Override
    public int getItemCount() {

        return mContacts.size();
    }

    /**
     * Helper class that creates a view holder.
     */
    public class ContactListViewHolder extends RecyclerView.ViewHolder {
        /**
         * View object.
         */
        public final View mView;

        /**
         * FragmentConcactListCardBinding object.
         */
        public FragmentContactlistCardBinding binding;

        /**
         * Constructor that creates the view holder.
         * @param view
         */
        public ContactListViewHolder(View view) {
            super(view);
            mView = view;

            binding = FragmentContactlistCardBinding.bind(view);
            binding.seeMoreButton.setOnClickListener(this::handleMoreOrLess);

        }

        /**
         * Method that makes a button show more of a card or show less.
         * @param button
         */
        private void handleMoreOrLess(final View button) {
            if (binding.contactPreviewText.getVisibility() == View.GONE) {
                binding.contactPreviewText.setVisibility(View.VISIBLE);
             //   binding.seeMoreButton.setImageIcon(Icon.createWithResource(mView.getContext(), R.drawable.ic_arrow_drop_up_black_24dp));
            } else {
                binding.contactPreviewText.setVisibility(View.GONE);
              // binding.seeMoreButton.setImageIcon(Icon.createWithResource(mView.getContext(), R.drawable.ic_arrow_drop_down_black_24dp));
            }
        }

        /**
         * Method that sets contacts.
         */
        void setContact(final Contacts contact) {


            // binding.alphabetLetterText.setText(contact.getAlphabet());

            // if (contact.getAlphabet().indexOf(0) == contact.getFirstName().charAt(0)) {


        //    binding.buttonFullPost.setOnClickListener(view -> Navigation.findNavController(mView).navigate(ContactsListFragmentDirections.actionContactListFragmentToContactsFragment(contact)));


        //    binding.memberidText.setText(contact.getMemberID());
            binding.contactUsernameText.setText(contact.getUserName());
//            binding.contactFirstNameText.setText(contact.getFirstName());
//            binding.contactLastNameText.setText(contact.getLastName());


            // }
        }
    }


}

