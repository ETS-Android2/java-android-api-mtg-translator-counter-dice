package mtg.java.mymagicapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import mtg.java.mymagicapp.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    static String firstUrl = "https://api.scryfall.com/cards/named?fuzzy=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String langlocale = preferences.getString("setLang", "en");
        FirstFragment.locale = langlocale;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_first, R.id.navigation_second)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void closeKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                final CharSequence[] items = {"Russian", "English", "Spanish", "French", "German", "Italian", "Portuguese", "Japanese", "Korean", "Simplified Chinese", "Traditional Chinese"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select you language:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item) {
                            case 0:
                                FirstFragment.locale = "ru";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 1:
                                FirstFragment.locale = "en";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 2:
                                FirstFragment.locale = "es";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 3:
                                FirstFragment.locale = "fr";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 4:
                                FirstFragment.locale = "de";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 5:
                                FirstFragment.locale = "it";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 6:
                                FirstFragment.locale = "pt";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 7:
                                FirstFragment.locale = "ja";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 8:
                                FirstFragment.locale = "ko";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 9:
                                FirstFragment.locale = "zhs";
                                FirstFragment.getCard(firstUrl);
                                break;
                            case 10:
                                FirstFragment.locale = "zht";
                                FirstFragment.getCard(firstUrl);
                                break;
                        }
                        dialog.dismiss();
                        saveLang();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.action_gamestats:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveLang() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("setLang", FirstFragment.locale);
        editor.apply();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}