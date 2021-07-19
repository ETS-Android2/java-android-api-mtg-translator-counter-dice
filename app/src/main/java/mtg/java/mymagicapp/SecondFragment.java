package mtg.java.mymagicapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import mtg.java.mymagicapp.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    int oneCounter = 20;
    int twoCounter = 20;

    String colorone = "#252424";
    String colortwo = "#DD1010";
    String colortree = "#11770F";
    String colorfour = "#B0DD32";
    String colorfive = "#3529C8";

    String saveColorOne;
    String saveColorTwo;

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button countPlus = binding.plusOne;
        final Button countMinus = binding.minusOne;
        final Button formatChange = binding.lifeNum;
        final TextView counterNum = binding.counterOne;

        final Button countPlusTwo = binding.plusTwo;
        final Button countMinusTwo = binding.minusTwo;
        final TextView counterNumTwo = binding.counterTwo;

        final Button backgroundOne = binding.backResetOne;
        final Button backgroundTwo = binding.backResetTwo;
        final CardView imageOne = binding.cardViewOne;
        final CardView imageTwo = binding.cardViewTwo;

        final Button resetCounters = binding.resetCouters;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        oneCounter = preferences.getInt("onePlayer", 20);
        twoCounter = preferences.getInt("twoPlayer", 20);
        saveColorOne = preferences.getString("onePlayerColor", "#DD1010");
        saveColorTwo = preferences.getString("twoPlayerColor", "#3529C8");

        counterNum.setText(Integer.toString(oneCounter));
        counterNumTwo.setText(Integer.toString(twoCounter));
        imageOne.setCardBackgroundColor(Color.parseColor(saveColorOne));
        imageTwo.setCardBackgroundColor(Color.parseColor(saveColorTwo));

        if (savedInstanceState != null) {
            oneCounter = savedInstanceState.getInt("RoneCounter",1);
            twoCounter = savedInstanceState.getInt("RtwoCounter",1);
        }

        backgroundOne.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                click.start();
                switch (i) {
                    case 0:saveColorOne = colorone;imageOne.setCardBackgroundColor(Color.parseColor(colorone));i++;break;
                    case 1:saveColorOne = colortwo;imageOne.setCardBackgroundColor(Color.parseColor(colortwo));i++;break;
                    case 2:saveColorOne = colortree;imageOne.setCardBackgroundColor(Color.parseColor(colortree));i++;break;
                    case 3:saveColorOne = colorfour;imageOne.setCardBackgroundColor(Color.parseColor(colorfour));i++;break;
                    case 4:saveColorOne = colorfive;imageOne.setCardBackgroundColor(Color.parseColor(colorfive));i++;break;
                } if (i >= 5) {
                    i = 0;
                }
                saveColor();
            }
        });

        backgroundTwo.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                click.start();
                switch (i) {
                    case 0:saveColorTwo = colorone;imageTwo.setCardBackgroundColor(Color.parseColor(colorone));i++;break;
                    case 1:saveColorTwo = colortwo;imageTwo.setCardBackgroundColor(Color.parseColor(colortwo));i++;break;
                    case 2:saveColorTwo = colortree;imageTwo.setCardBackgroundColor(Color.parseColor(colortree));i++;break;
                    case 3:saveColorTwo = colorfour;imageTwo.setCardBackgroundColor(Color.parseColor(colorfour));i++;break;
                    case 4:saveColorTwo = colorfive;imageTwo.setCardBackgroundColor(Color.parseColor(colorfive));i++;break;
                } if (i >= 5) {
                    i = 0;
                }
                saveColor();
            }
        });

        countPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                    click.start();
                    oneCounter++;
                    saveBase();
                    counterNum.setText(Integer.toString(oneCounter));
                }
            });

        countMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                    click.start();
                    oneCounter--;
                    saveBase();
                    counterNum.setText(Integer.toString(oneCounter));
            }
        });

        countPlusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                click.start();
                twoCounter++;
                saveBase();
                counterNumTwo.setText(Integer.toString(twoCounter));
            }
        });

        countMinusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                click.start();
                twoCounter--;
                saveBase();
                counterNumTwo.setText(Integer.toString(twoCounter));

            }
        });

        formatChange.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                click.start();

                switch (i) {
                    case 0:
                        oneCounter = 30;
                        twoCounter = 30;
                        i++;
                        break;
                    case 1:
                        oneCounter = 40;
                        twoCounter = 40;
                        i++;
                        break;
                    case 2:
                        oneCounter = 20;
                        twoCounter = 20;
                        i++;
                        break;
                } if (i >= 3) {
                    i = 0;
                }

                saveBase();
                counterNumTwo.setText(Integer.toString(twoCounter));
                counterNum.setText(Integer.toString(oneCounter));
            }
        });

        resetCounters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer click = MediaPlayer.create(getActivity(), R.raw.click);
                click.start();
                twoCounter = 20;
                oneCounter = 20;
                saveBase();
                counterNum.setText(Integer.toString(20));
                counterNumTwo.setText(Integer.toString(twoCounter));
            }
        });
    }

    private void saveBase() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("onePlayer", oneCounter);
        editor.putInt("twoPlayer", twoCounter);
        editor.apply();
    }


    private void saveColor() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("onePlayerColor", saveColorOne);
        editor.putString("twoPlayerColor", saveColorTwo);
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("RoneCounter", oneCounter);
        outState.putInt("RtwoCounter", twoCounter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}