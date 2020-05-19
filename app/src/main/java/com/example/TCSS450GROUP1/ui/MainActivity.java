package com.example.TCSS450GROUP1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.ActivityMainBinding;
import com.example.TCSS450GROUP1.model.NewMessageCountViewModel;
import com.example.TCSS450GROUP1.model.PushyTokenViewModel;
import com.example.TCSS450GROUP1.model.UserInfoViewModel;
import com.example.TCSS450GROUP1.ui.chat.PushReceiver;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.TCSS450GROUP1.ui.chat.ChatMessage;
import com.example.TCSS450GROUP1.ui.chat.ChatViewModel;
/**
 * Activity where most fragments take place
 @author Joseph Rushford
 */
public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private MainPushMessageReceiver mPushMessageReceiver;
    private NewMessageCountViewModel getmNewMessageModel;
    private ActivityMainBinding binding;
    private NewMessageCountViewModel mNewMessageModel;
    private SharedPreferences.Editor sharedTheme;
    private SharedPreferences  mSharedTheme;
    private static final String THEME_KEY = "currentTheme";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(getSavedTheme());


        mSharedTheme = getSharedPreferences("currentTheme", MODE_PRIVATE);
        mNewMessageModel = new ViewModelProvider(this).get(NewMessageCountViewModel.class);
        //getTheme().applyStyle(R.style.OverlayThemePink, true);
        mSharedTheme.getString(THEME_KEY, "default");
        if(mSharedTheme.getString(THEME_KEY, "default").equals("default")) {
            getTheme().applyStyle(R.style.AppTheme, true);
        } else {
            getTheme().applyStyle(R.style.OverlayThemePink, true);
        }
        super.onCreate(savedInstanceState);
        //boolean theme = preferences.getBoolean("theme", false);
//
//        if(theme) {
//            setTheme(R.style.AppTheme_Dark_NoActionBar);
//       }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MainActivityArgs args = MainActivityArgs.fromBundle(getIntent().getExtras());
        String email = args.getEmail();

        new ViewModelProvider(this, new UserInfoViewModel.UserInfoViewModelFactory(args.getEmail(), args.getJwt(), "temp" )).get(UserInfoViewModel.class);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home,R.id.navigation_settings,
                R.id.navigation_chat, R.id.navigation_connections, R.id.navigation_weather).build();
                //R.id.navigation_weather).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.navigation_chat) {
                //When the user navigates to the chats page, reset the new message count.
                //This will need some extra logic for your project as it should have
                //multiple chat rooms.
                mNewMessageModel.reset();
            }
        });
        mNewMessageModel.addMessageCountObserver(this, count -> {
            BadgeDrawable badge = binding.navView.getOrCreateBadge(R.id.navigation_chat);
            badge.setMaxCharacterCount(2);
            if (count > 0) {
                //new messages! update and show the notification badge.
                badge.setNumber(count);
                badge.setVisible(true);
            } else {
                //user did some action to clear the new messages, remove the badge
                badge.clearNumber();
                badge.setVisible(false);
            }
        });

    }

    /**
     * @author Joseph Rushford
     * @return navigation bar
     */
    @Override
    public boolean onSupportNavigateUp(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drop_down, menu);

        return true;
    }

    /**
     * Creates setting drop down menu with on clicklisteners
     * @author Joseph Rushford
     * @param item the item being clicked in the menu
     * @return which item is being clicked
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                signOut();
                break;
            case R.id.action_change_password:
                changePassword();
                break;
            case R.id.action_delete:
                deleteAccount();
                break;
            case R.id.action_defaultColor:
                mSharedTheme.edit().putString(THEME_KEY, "default").apply();
                //recreate();
                Intent intent= getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
              //  toggleTheme(false);
                break;
            case R.id.action_PINK:
                mSharedTheme.edit().putString(THEME_KEY, "pink").apply();
                //recreate();
                Intent intentPink = getIntent();
                intentPink.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intentPink);
              //  toggleTheme(true);
                break;
            default:
                super.onOptionsItemSelected(item);

        }
        return false;
    }
//
//

    /**
     * @author Joseph Rushford
     * used for the menu item delete account not currently functioning
     */
    private void deleteAccount() {

    }


    /**
     * @author Joseph Rushford
     * used for menu item to change password current in bottomnav instead
     */
    private void changePassword() {
    }

    /**
     * @author Joseph Rushford
     * helper method for menu item to log out user.
     */
    private void signOut() {
        SharedPreferences prefs =
                getSharedPreferences(
                        getString(R.string.keys_shared_prefs),
                        Context.MODE_PRIVATE);

        prefs.edit().remove(getString(R.string.keys_prefs_jwt)).apply();
        //End the app completely
        PushyTokenViewModel model = new ViewModelProvider(this)
                .get(PushyTokenViewModel.class);
        //when we hear back from the web service quit
        model.addResponseObserver(this, result -> finishAndRemoveTask());

        model.deleteTokenFromWebservice(
                new ViewModelProvider(this)
                        .get(UserInfoViewModel.class)
                        .getJWT()
        );
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mPushMessageReceiver == null) {
            mPushMessageReceiver = new MainPushMessageReceiver();
        }
        IntentFilter iFilter = new IntentFilter(PushReceiver.RECEIVED_NEW_MESSAGE);
        registerReceiver(mPushMessageReceiver, iFilter);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mPushMessageReceiver != null){
            unregisterReceiver(mPushMessageReceiver);
        }
    }
    /**
     * @author Joseph Rushford
     * A BroadcastReceiver that listens for messages sent from PushReceiver
     */
    private class MainPushMessageReceiver extends BroadcastReceiver {
        private ChatViewModel mModel =
                new ViewModelProvider(MainActivity.this)
                        .get(ChatViewModel.class);
        @Override
        public void onReceive(Context context, Intent intent) {
            NavController nc =
                    Navigation.findNavController(
                            MainActivity.this, R.id.nav_host_fragment);
            NavDestination nd = nc.getCurrentDestination();
            if (intent.hasExtra("chatMessage")) {
                ChatMessage cm = (ChatMessage) intent.getSerializableExtra("chatMessage");
                //If the user is not on the chat screen, update the
                // NewMessageCountView Model
                if (nd.getId() != R.id.navigation_chat) {
                    mNewMessageModel.increment();
                }
                //Inform the view model holding chatroom messages of the new
                //message.
                mModel.addMessage(intent.getIntExtra("chatid", -1), cm);
            }
        }
    }

}
