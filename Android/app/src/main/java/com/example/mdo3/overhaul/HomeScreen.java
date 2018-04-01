package com.example.mdo3.overhaul;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


/**
 * A login screen that offers login via email/password.
 */
public class HomeScreen extends AppCompatActivity
{

    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    private UserLoginTask mAuthTask = null;
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Switch loginSwitch;
    private boolean DEBUG = true;
    private UserDetails usrDets = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mEmailView = (EditText) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.login_password);
        loginSwitch = (Switch) findViewById(R.id.login_switch);

        mLoginFormView = findViewById(R.id.login_main_layout);
        mProgressView = findViewById(R.id.login_progress);

        loginSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                    loginSwitch.setText(R.string.user_driver);
                else
                    loginSwitch.setText(R.string.user_customer);
            }
        });

        //Sign Up Button
        OnClickListener signUpListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just a placeholder so I can test that clicking on this button actually works.
                Toast.makeText(HomeScreen.this, "Not ready for sign up yet!", Toast.LENGTH_SHORT).show();
            }
        };
        Button signUpBtn = (Button) findViewById(R.id.button_sign_up);
        signUpBtn.setOnClickListener(signUpListen);

        //Login Button
        OnClickListener loginListen = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Currently hardcoded login process. Once we get the process of confirming accounts via the database
                // set up, we would replace this whole if-block with a call to the function that actually handles the
                // login processing (or whatever else may be required). Setting the Intent may still be required.

                // Currently the credentials are based off of one of the dummy credentials listed with the other main variables.
                if (mEmailView.getText().toString().equals("foo@example.com") ) {
                    if (mPasswordView.getText().toString().equals("hello") ) {

                        if (loginSwitch.isChecked()){
                            // If the switch is checked, that means the text is set to Driver. Go to the Driver main screen.
                            finish();
                            Intent myIntent = new Intent(HomeScreen.this, DriverMainScreen.class);
                            HomeScreen.this.startActivity(myIntent);
                        }
                        else {
                            // Otherwise, that means the switch is still showing Customer, so go to the CUstomer main screen.
                            finish();
                            Intent myIntent = new Intent(HomeScreen.this, ClientMainScreen.class);
                            HomeScreen.this.startActivity(myIntent);
                        }
                    }
                    else {
                        // Just a placeholder for now
                        Toast.makeText(HomeScreen.this, "Please input correct credentials! hello", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    // Just a placeholder for now
                    Toast.makeText(HomeScreen.this, "Please input correct credentials! foo@example.com and hello", Toast.LENGTH_SHORT).show();
                }

                // TODO: Modify this function to determine whether to go to the Client or Driver main screen!!
            }
        };
        Button loginBtn = (Button) findViewById(R.id.button_log_in);
        loginBtn.setOnClickListener(loginListen);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin()
    {
        if (mAuthTask != null)
        {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password))
        {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email))
        {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email))
        {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else
        {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email)
    {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password)
    {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show)
    {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean>
    {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password)
        {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            usrDets = null;
            try
            {
               DataAcess DA = new DataAcess();
                usrDets = DA.checkUserLogin(mEmail,mPassword);
                Thread.sleep(2000);

            } catch (InterruptedException e)
            {
                return false;
            }

            return (usrDets != null ? true : false);
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            mAuthTask = null;
            showProgress(false);

            if (success)
            {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled()
        {
            mAuthTask = null;
            showProgress(false);
        }
    }

    public void loginSwitch(View view)
    {

    }

    public void loginBtn(View view)
    {
        //TODO: Add intent
        if(DEBUG){System.out.println("DEBUG: Login Button Button Pressed");}
        Intent intent = new Intent(this, JobRequest.class);
       startActivity(intent);


    }
    public void signUp(View view)
    {
        //TODO: add intent
        if(DEBUG){System.out.println("DEBUG: Signup Button Pressed");}
        //Intent intent = new Intent();
        //startActivity(intent);
    }
}

