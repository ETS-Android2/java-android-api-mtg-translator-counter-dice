package mtg.java.mymagicapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import mtg.java.mymagicapp.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private int oneCounter = 20;
    private int twoCounter = 20;

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            oneCounter = savedInstanceState.getInt("RoneCounter",0 );
            twoCounter = savedInstanceState.getInt("RtwoCounter",0);
        }

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

        backgroundOne.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
                switch (i) {
                    case 0:imageOne.setBackgroundResource(R.drawable.dark);i++;break;
                    case 1:imageOne.setBackgroundResource(R.drawable.fire);i++;break;
                    case 2:imageOne.setBackgroundResource(R.drawable.forest);i++;break;
                    case 3:imageOne.setBackgroundResource(R.drawable.sky);i++;break;
                    case 4:imageOne.setBackgroundResource(R.drawable.water);i++;break;
                } if (i>=5) {
                    i = 0;
                }
            }
        });

        backgroundTwo.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
                switch (i) {
                    case 0:imageTwo.setBackgroundResource(R.drawable.dark);i++;break;
                    case 1:imageTwo.setBackgroundResource(R.drawable.fire);i++;break;
                    case 2:imageTwo.setBackgroundResource(R.drawable.forest);i++;break;
                    case 3:imageTwo.setBackgroundResource(R.drawable.sky);i++;break;
                    case 4:imageTwo.setBackgroundResource(R.drawable.water);i++;break;
                } if (i>=5) {
                    i = 0;
                }
            }
        });

        countPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    oneCounter++;
                    counterNum.setText(Integer.toString(oneCounter));
                }
            });

        countMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    oneCounter--;
                    counterNum.setText(Integer.toString(oneCounter));
            }
        });

        countPlusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoCounter++;
                counterNumTwo.setText(Integer.toString(twoCounter));
            }
        });

        countMinusTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoCounter--;
                counterNumTwo.setText(Integer.toString(twoCounter));
            }
        });
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