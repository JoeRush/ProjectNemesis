package com.example.TCSS450GROUP1.ui.auth.login;

import android.os.Bundle;

<<<<<<< HEAD
import androidx.fragment.app.Fragment;
=======
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
>>>>>>> Joseph's-Branch

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.TCSS450GROUP1.R;
<<<<<<< HEAD
=======
import com.example.TCSS450GROUP1.databinding.FragmentLoginBinding;
<<<<<<< HEAD
>>>>>>> Joseph's-Branch
=======
import com.example.TCSS450GROUP1.util.PasswordValidator;

import org.json.JSONException;
import org.json.JSONObject;
>>>>>>> Joseph's-Branch

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
<<<<<<< HEAD
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
<<<<<<< HEAD
=======
=======
        binding = FragmentLoginBinding.inflate(inflater, container, false);
>>>>>>> Joseph's-Branch

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

    private void processRegister() {
        Navigation.findNavController(getView()).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());

    }
    private void attemptSignIn(final View button) {
        validateEmail();
    }
    private void validateEmail() {
        mEmailValidator.processResult(
                mEmailValidator.apply(binding.editEmail.getText().toString().trim()),
                this::validatePassword,
                result -> binding.editEmail.setError("Please enter a valid Email address."));
    }
    private void validatePassword() {
        mPassWordValidator.processResult(
                mPassWordValidator.apply(binding.editPassword.getText().toString()),
                this::verifyAuthWithServer,
                result -> binding.editPassword.setError("Please enter a valid Password."));
    }
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
                    navigateToSuccess(
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

    private void navigateToSuccess(String toString, String token) {
        Navigation.findNavController(getView()).navigate(

                LoginFragmentDirections.actionLoginFragmentToMainActivity(toString, token));

    }



>>>>>>> Joseph's-Branch
}
