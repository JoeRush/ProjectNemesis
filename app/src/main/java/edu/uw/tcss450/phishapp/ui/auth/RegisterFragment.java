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

import edu.uw.tcss450.phishapp.databinding.FragmentRegisterBinding;
import edu.uw.tcss450.phishapp.util.CheckInput;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //lamba expression example
        binding.buttonReg.setOnClickListener((button->processSuccess(binding.regEmailText.getText().toString())));
    }

    private void processSuccess(String email) {
        CheckInput theEmailState = new CheckInput(email, 0);
        String pass = binding.passwordRegText.getText().toString();
        String redoPass = binding.confirmPasswordText.getText().toString();
        CheckInput thePasswordState = new CheckInput(pass, 1);
        CheckInput theRedoPasswordState = new CheckInput(redoPass, 1);
        boolean twoPass = comparePasswords(pass, redoPass);
        if(theEmailState.getState() && thePasswordState.getState() && theRedoPasswordState.getState() && twoPass) {
                Navigation.findNavController(getView()).navigate(
                        RegisterFragmentDirections
                                .actionRegisterFragmentToMainActivity(binding.regEmailText.getText().toString(), "")
                );
        }
        if(!theEmailState.getState()){
            Log.d("EMAIL" , " Invalid Email: " + theEmailState.getErrorMessage());
            binding.regEmailText.setError(theEmailState.getErrorMessage());
        }
        if(!thePasswordState.getState()) {
            binding.passwordRegText.setError(thePasswordState.getErrorMessage());
        }
        if(!theRedoPasswordState.getState()) {
            binding.confirmPasswordText.setError(thePasswordState.getErrorMessage());
        }
        if(!twoPass) {
            String passError = thePasswordState.getErrorMessage().concat("\nPasswords do not match");
            String redoError = theRedoPasswordState.getErrorMessage().concat("\nPasswords do not match");
            binding.passwordRegText.setError(passError);
            binding.confirmPasswordText.setError(redoError);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private boolean comparePasswords(String password, String redo) {
        boolean state = true;
        if(!(password.trim().compareTo(redo.trim()) == 0)) {
            state = false;
        }
        return state;
    }

}
