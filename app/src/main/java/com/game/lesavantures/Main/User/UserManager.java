package com.game.lesavantures.Main.User;

import android.util.Log;

import com.game.lesavantures.Main.GameManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import androidx.annotation.NonNull;

public class UserManager {

    /**
     * The currently logged in user.
     */
    private User loggedInUser = null;

    /**
     * the firebase authentication object used to access the database
     */
    private FirebaseAuth mAuth;

    /**
     * TAG for logging purposes.
     */
    private String TAG = "USER_MANAGER";

    public UserManager() {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * login user with Firebase, using a given email and password. Call the callback functions defined in the AuthActivity abstract class according to result.
     * @param email
     * @param password
     */
    public void loginUser(final AuthActivity fromAuthActivity, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(fromAuthActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // get FB user
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

                            // sign in user
                            loggedInUser = new User(firebaseUser);

                            // "callback" to handle the login as planned
                            fromAuthActivity.onLoginSuccess();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            fromAuthActivity.onLoginError(task.getException());
                        }
                    }

                });
    }

    /**
     * sign up the user using Firebase, calling the correct "callback" methods as defined in the AuthActivity
     * @param email
     * @param password
     */
    public void signUpUser(final AuthActivity fromAuthActivity, String email, String password, final String displayName) {
        try {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(fromAuthActivity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // sign in user
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                User user = new User(firebaseUser);
                                loggedInUser = user;
                                setUserDisplayName(displayName);
                                GameManager.statisticsManager.initUserInDatabase();
                                fromAuthActivity.onSignUpSuccess();

                            } else {
                                // If sign in fails, call "callback"
                                fromAuthActivity.onSignUpError(task.getException());
                            }
                        }
                    });
        } catch (Exception e) {
            fromAuthActivity.onSignUpError(new Exception("Something didn't work here :/"));
            e.printStackTrace();
        }
    }

    public void setUserDisplayName(String displayName) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(displayName).build();
        loggedInUser.getFirebaseUser().updateProfile(profileUpdates);
    }

    /**
     * Log out the current user
     */
    public void logoutCurrentUser() {
        mAuth.signOut();
        this.loggedInUser = null;
    }

    /**
     * @return the currently logged in user
     */
    public User getLoggedInUser() {
        return this.loggedInUser;
    }
}
