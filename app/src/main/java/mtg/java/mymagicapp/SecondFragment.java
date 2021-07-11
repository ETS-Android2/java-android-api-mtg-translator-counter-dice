package mtg.java.mymagicapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import mtg.java.mymagicapp.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private int oneCounter = 20;
    private int twoCounter = 20;

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}