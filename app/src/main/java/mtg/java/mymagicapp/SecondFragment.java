package mtg.java.mymagicapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import mtg.java.mymagicapp.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    int oneCounter = 20;
    int twoCounter = 20;

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button countPlus = binding.plusOne;
        final Button countMinus = binding.minusOne;
        final TextView counterNum = binding.counterOne;

        final Button countPlusTwo = binding.plusTwo;
        final Button countMinusTwo = binding.minusTwo;
        final TextView counterNumTwo = binding.counterTwo;

        final Button backgroundOne = binding.backResetOne;
        final Button backgroundTwo = binding.backResetTwo;
        final LinearLayout imageOne = binding.opponentOne;
        final LinearLayout imageTwo = binding.opponentTwo;

        final Button resetCounters = binding.resetCouters;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        oneCounter = preferences.getInt("onePlayer", 20);
        twoCounter = preferences.getInt("twoPlayer", 20);
        counterNum.setText(Integer.toString(oneCounter));
        counterNumTwo.setText(Integer.toString(twoCounter));

        if (savedInstanceState != null) {
            oneCounter = savedInstanceState.getInt("RoneCounter",1);
            twoCounter = savedInstanceState.getInt("RtwoCounter",1);
        }

        backgroundOne.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                switch (i) {
                    case 0:imageOne.setBackgroundResource(R.drawable.dark);i++;break;
                    case 1:imageOne.setBackgroundColor(Color.parseColor("#252424"));i++;break;
                    case 2:imageOne.setBackgroundResource(R.drawable.fire);i++;break;
                    case 3:imageOne.setBackgroundColor(Color.parseColor("#DD1010"));i++;break;
                    case 4:imageOne.setBackgroundResource(R.drawable.forest);i++;break;
                    case 5:imageOne.setBackgroundColor(Color.parseColor("#11770F"));i++;break;
                    case 6:imageOne.setBackgroundResource(R.drawable.sky);i++;break;
                    case 7:imageOne.setBackgroundColor(Color.parseColor("#B0DD32"));i++;break;
                    case 8:imageOne.setBackgroundResource(R.drawable.water);i++;break;
                    case 9:imageOne.setBackgroundColor(Color.parseColor("#3529C8"));i++;break;
                } if (i >= 10) {
                    i = 0;
                }
            }
        });

        backgroundTwo.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View view) {
                switch (i) {
                    case 0:imageTwo.setBackgroundResource(R.drawable.dark);i++;break;
                    case 1:imageTwo.setBackgroundColor(Color.parseColor("#252424"));i++;break;
                    case 2:imageTwo.setBackgroundResource(R.drawable.fire);i++;break;
                    case 3:imageTwo.setBackgroundColor(Color.parseColor("#DD1010"));i++;break;
                    case 4:imageTwo.setBackgroundResource(R.drawable.forest);i++;break;
                    case 5:imageTwo.setBackgroundColor(Color.parseColor("#11770F"));i++;break;
                    case 6:imageTwo.setBackgroundResource(R.drawable.sky);i++;break;
                    case 7:imageTwo.setBackgroundColor(Color.parseColor("#B0DD32"));i++;break;
                    case 8:imageTwo.setBackgroundResource(R.drawable.water);i++;break;
                    case 9:imageTwo.setBackgroundColor(Color.parseColor("#3529C8"));i++;break;
                } if (i >= 10) {
                    i = 0;
                }
            }
        });

        countPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneCounter++;
                    saveBase();
                    counterNum.setText(Integer.toString(oneCounter));
                }
            });

        countMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    oneCounter--;
                    saveBase();
                    counterNum.setText(Integer.toString(oneCounter));
            }
        });

        countPlusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoCounter++;
                saveBase();
                counterNumTwo.setText(Integer.toString(twoCounter));
            }
        });

        countMinusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoCounter--;
                saveBase();
                counterNumTwo.setText(Integer.toString(twoCounter));

            }
        });

        resetCounters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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