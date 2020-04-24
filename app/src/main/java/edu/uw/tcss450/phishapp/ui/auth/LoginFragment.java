package edu.uw.tcss450.phishapp.ui.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import edu.uw.tcss450.phishapp.databinding.FragmentLoginBinding;
import edu.uw.tcss450.phishapp.util.CheckInput;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //lamba expression example
        binding.registerButton.setOnClickListener(button->processRegister());
        binding.signInButton.setOnClickListener((button->processSuccess(binding.emailText.getText().toString())));
    }

    private void processRegister() {
        Navigation.findNavController(getView()).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());

    }
    private void processSuccess(String email) {
        CheckInput theEmailState = new CheckInput(email, 0);
        CheckInput thePasswordState = new CheckInput(binding.passwordText.getText().toString(), 1);
        if(theEmailState.getState() && thePasswordState.getState()) {
            Navigation.findNavController(getView()).navigate(
                    LoginFragmentDirections.actionLoginFragmentToMainActivity(binding.emailText.getText().toString(), ""));

            //LoginFragmentDirections.ActionLoginFragmentToSuccessFragment directions = LoginFragmentDirections.actionLoginFragmentToSuccessFragment(email, "");
           // Navigation.findNavController(getView()).navigate(directions);
        }
        if(!theEmailState.getState()){
            Log.d("EMAIL" , " Invalid Email: " + theEmailState.getErrorMessage());
            binding.emailText.setError(theEmailState.getErrorMessage());
        }
        if(!thePasswordState.getState()) {
            binding.passwordText.setError(thePasswordState.getErrorMessage());
        }

    }


}
