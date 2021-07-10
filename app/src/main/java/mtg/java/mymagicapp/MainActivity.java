package mtg.java.mymagicapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import mtg.java.mymagicapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    static String firstUrl = "https://api.scryfall.com/cards/named?fuzzy=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

   //     setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(R.id.FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    public static void closeKeyboard() {
//        View view = getCurrentFocus();
//        if (view != null) {
//            InputMethodManager manager
//                    = (InputMethodManager)
//                    getSystemService(
//                            Context.INPUT_METHOD_SERVICE);
//            manager
//                    .hideSoftInputFromWindow(
//                            view.getWindowToken(), 0);
//        }
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final TextView selectedText = findViewById(R.id.textview_first);

        switch (item.getItemId()) {
            case R.id.action_settings:
                final CharSequence[] items = {"Russian", "English", "Spanish", "French", "German", "Italian", "Portuguese", "Japanese", "Korean", "Simplified Chinese", "Traditional Chinese"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select you language:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        FirstFragment fragment1 = (FirstFragment)getSupportFragmentManager()
                                .findFragmentById(R.id.FirstFragment);

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

                        selectedText.setText("Translate from en to: " + fragment1.locale);
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.action_newgame:
                return true;
            case R.id.action_gamestats:
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}