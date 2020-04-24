// Generated by view binder compiler. Do not edit!
package edu.uw.tcss450.phishapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import edu.uw.tcss450.phishapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRegisterBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonReg;

  @NonNull
  public final EditText confirmPasswordText;

  @NonNull
  public final EditText passwordRegText;

  @NonNull
  public final EditText regEmailText;

  @NonNull
  public final ConstraintLayout registerLayout;

  private FragmentRegisterBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonReg,
      @NonNull EditText confirmPasswordText, @NonNull EditText passwordRegText,
      @NonNull EditText regEmailText, @NonNull ConstraintLayout registerLayout) {
    this.rootView = rootView;
    this.buttonReg = buttonReg;
    this.confirmPasswordText = confirmPasswordText;
    this.passwordRegText = passwordRegText;
    this.regEmailText = regEmailText;
    this.registerLayout = registerLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    String missingId;
    missingId: {
      Button buttonReg = rootView.findViewById(R.id.button_reg);
      if (buttonReg == null) {
        missingId = "buttonReg";
        break missingId;
      }
      EditText confirmPasswordText = rootView.findViewById(R.id.confirm_password_text);
      if (confirmPasswordText == null) {
        missingId = "confirmPasswordText";
        break missingId;
      }
      EditText passwordRegText = rootView.findViewById(R.id.password_reg_text);
      if (passwordRegText == null) {
        missingId = "passwordRegText";
        break missingId;
      }
      EditText regEmailText = rootView.findViewById(R.id.reg_email_text);
      if (regEmailText == null) {
        missingId = "regEmailText";
        break missingId;
      }
      ConstraintLayout registerLayout = rootView.findViewById(R.id.register_layout);
      if (registerLayout == null) {
        missingId = "registerLayout";
        break missingId;
      }
      return new FragmentRegisterBinding((ConstraintLayout) rootView, buttonReg,
          confirmPasswordText, passwordRegText, regEmailText, registerLayout);
    }
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}