package com.example.TCSS450GROUP1.ui.connections.contacts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.TCSS450GROUP1.databinding.FragmentContactsListBinding;
import com.example.TCSS450GROUP1.model.UserInfoViewModel;
import com.example.TCSS450GROUP1.ui.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsListFragment extends Fragment {
    /**
     * ContactListViewModel object.
     */
    private ContactListViewModel mModel;
    private UserInfoViewModel mUserModel;
    /**
     * FragmentContactListBinding object.
     */
    private FragmentContactsListBinding binding;

    /**
     * Empty public constructor.
     */
    public ContactsListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new ViewModelProvider(getActivity()).get(ContactListViewModel.class);
        if (getActivity() instanceof MainActivity){
            MainActivity activity = (MainActivity) getActivity();
          //  mModel.setUserInfoViewModel(activity.getUserInfoViewModel());
        }
        mModel.connectGet();

    }

    /**
     * Instantiates contact fragment UI view.
     *
     * @param inflater           object to inflate any view in layout.
     * @param container          parent view fragment UI is attached to.
     * @param savedInstanceState reconstructed fragment from previous saved state.
     * @return home fragment view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContactsListBinding.inflate(inflater);

        return binding.getRoot();
    }

    /**
     * Contact fragment view constructor
     *
     * @param view               returned by onCreateView.
     * @param savedInstanceState reconstructed fragment from previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        mModel.connect(mUserModel.getEmail(), mUserModel.getJWT());
        //listOfContacts();
//        mModel.addResponseObserver(getViewLifecycleOwner(),
//                this::observeContacts);

    }

//    private void observeContacts(JSONObject response) {
//        Log.i("user", response.toString());
//        if (response.length() > 0) {
//            if (response.has("code")) {
//                try {
////                    binding.contactNames.setText(
////                            mUserModel.getEmail());
//                    binding.contactNames.setError(
//                            "Error Authenticating: " +
//                                    response.getJSONObject("data").getString("message"));
//                } catch (JSONException e) {
//                    Log.e("JSON Parse Error", e.getMessage());
//                }
//            } else {
//                try {
//                    getContacts(response);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        } else {
//            Log.d("JSON Response", "No Response");
//        }
//    }

//        FragmentContactsListBinding binding = FragmentContactsListBinding.bind(getView());
//
//        mModel.addContactsListObserver(getViewLifecycleOwner(), contactList -> {
//
//            if (!contactList.isEmpty()) {
////                binding.listRoot.setAdapter(new ContactListRecyclerViewAdapter(list));
//                binding.listRoot.setAdapter(new ContactListRecyclerViewAdapter(contactList));
//
//            }
//        });
//    }
}

