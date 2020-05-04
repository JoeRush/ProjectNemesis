package com.example.TCSS450GROUP1.ui.auth.login;

import android.os.Bundle;

<<<<<<< HEAD
import androidx.fragment.app.Fragment;
=======
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
>>>>>>> Joseph's-Branch

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TCSS450GROUP1.R;
<<<<<<< HEAD
=======
import com.example.TCSS450GROUP1.databinding.FragmentLoginBinding;
>>>>>>> Joseph's-Branch

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
<<<<<<< HEAD
=======

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Local access to the ViewBinding object. No need to create as Instance Var as it is only
        //used here.
        FragmentLoginBinding binding = FragmentLoginBinding.bind(getView());

        //On button click, navigate to MainActivity
        binding.buttonSignIn.setOnClickListener(button ->
                Navigation.findNavController(getView()).navigate(
                        LoginFragmentDirections
                                .actionLoginFragmentToMainActivity()));

        binding.buttonToRegister.setOnClickListener(button ->
                Navigation.findNavController(getView()).navigate(
                        LoginFragmentDirections
                                .actionLoginFragmentToRegisterFragment()));
    }



>>>>>>> Joseph's-Branch
}
