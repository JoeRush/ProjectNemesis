package edu.uw.tcss450.phishapp.ui.success;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import edu.uw.tcss450.phishapp.R;
import edu.uw.tcss450.phishapp.databinding.FragmentSuccessBinding;
import edu.uw.tcss450.phishapp.model.UserInfoViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuccessFragment extends Fragment {



    public SuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //binding = FragmentSuccessBinding.inflate(inflater, container, false);



        return inflater.inflate(R.layout.fragment_success, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentSuccessBinding binding = FragmentSuccessBinding.bind(getView());
        UserInfoViewModel model = new ViewModelProvider(getActivity()).get(UserInfoViewModel.class);
        binding.emailSuccess.setText("Welcome " + model.getEmail());
        //SuccessFragmentArgs args = SuccessFragmentArgs.fromBundle(getArguments());
        //binding.emailSuccess.setText(args.getEmailPassed());
//        NavController navController = Navigation.findNavController(view);
//        if(!navController.popBackStack()) {
//            finish();
//
//        }
    }

}
