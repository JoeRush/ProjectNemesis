package com.example.TCSS450GROUP1.ui.auth.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentLoginBinding;
import com.example.TCSS450GROUP1.util.PasswordValidator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel mLoginModel;
    private PasswordValidator mEmailValidator = PasswordValidator.checkPwdLength(2)
            .and(PasswordValidator.checkExcludeWhiteSpace())
            .and(PasswordValidator.checkPwdSpecialChar("@"));
    private PasswordValidator mPassWordValidator = PasswordValidator.checkPwdLength(7)
            .and(PasswordValidator.checkExcludeWhiteSpace());
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginModel = new ViewModelProvider(getActivity())
                .get(LoginViewModel.class);
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
        binding.buttonToRegister.setOnClickListener(button->processRegister());
        binding.buttonSignIn.setOnClickListener(this::attemptSignIn);
        mLoginModel.addResponseObserver(
                getViewLifecycleOwner(),
                this::observeResponse);
    }

    /**
     * Private helper method to navigate to register.
     */
    private void processRegister() {
        Navigation.findNavController(getView()).
                navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());

    }

    /**
     * Private helper method to attempt to sign in.
     * @param button the button being clicked.
     */
    private void attemptSignIn(final View button) {
        validateEmail();
    }

    /**
     * private helper to check if the user's email is valid.
     */
    private void validateEmail() {
        mEmailValidator.processResult(
                mEmailValidator.apply(binding.editEmail.getText().toString().trim()),
                this::validatePassword,
                result -> binding.editEmail.setError("Please enter a valid Email address."));
    }

    /**
     * private helper to check if the user's password is valid
     */
    private void validatePassword() {
        mPassWordValidator.processResult(
                mPassWordValidator.apply(binding.editPassword.getText().toString()),
                this::verifyAuthWithServer,
                result -> binding.editPassword.setError("Please enter a valid Password."));
    }

    /**
     * private helper method to verify user exists in database.
     */
    private void verifyAuthWithServer() {
        mLoginModel.connect(
                binding.editEmail.getText().toString(),
                binding.editPassword.getText().toString()
        );

    }
    /**
     * An observer on the HTTP Response from the web server. This observer should be
     * attached to SignInViewModel.
     *
     * @param response the Response from the server
     */
    private void observeResponse(final JSONObject response) {
        if(response.length() > 0) {
            if(response.has("code")) {
                try {
                    binding.editEmail.setError(
                            "Error Authenticating: " +
                                    response.getJSONObject("data").getString("message")
                    );
                } catch (JSONException e) {
                    Log.e("JSON Error", e.getMessage());
                }
            } else {
                try {
                    navigateToHome(
                            binding.editEmail.getText().toString(),
                            response.getString("token")
                    );
                } catch (JSONException e) {
                    Log.e("JSON Parse Error", e.getMessage());
                }
            }
        } else {
            Log.d("JSON Response", "No Response");
        }

    }

    /**
     * Private helper method to navigate to main activity
     * @param toString the user's email.
     * @param token the jwt generated.
     */
    private void navigateToHome(String toString, String token) {
        Navigation.findNavController(getView()).navigate(

                LoginFragmentDirections.actionLoginFragmentToMainActivity(toString, token));

    }



}
