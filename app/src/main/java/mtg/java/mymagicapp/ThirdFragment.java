package mtg.java.mymagicapp;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import mtg.java.mymagicapp.databinding.FragmentThirdBinding;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
    int onePlayerPoint = 0;
    int twoPlayerPoint = 0;
    int twentyPoint = 0;
    Random r;

    ImageView dOnePlay;
    ImageView dTwoPlay;
    ImageView dTwenty;
    TextView dTwentyNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
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

        dOnePlay = binding.onePlayerD;
        dTwoPlay = binding.twoPlayerD;
        dTwenty = binding.dTwenty;
        dTwentyNum = binding.dTwentyNum;

        r = new Random();

        dOnePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int onePlayerTrow = r.nextInt(6) + 1;
                setImageOne(onePlayerTrow);
                Animation rotateUp = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_two);
                final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.dice);
                mp.start();
                dOnePlay.startAnimation(rotateUp);

                int twentyPlayerTrow = r.nextInt(20) + 1;
                Animation rotateRevert = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_tree);
                dTwenty.startAnimation(rotateRevert);
                dTwentyNum.setRotation(-180);
                dTwentyNum.setText(Integer.toString(twentyPlayerTrow));
            }
        });

        dTwoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int twoPlayerTrow = r.nextInt(6) + 1;
                setImageTwo(twoPlayerTrow);
                Animation rotateDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_one);
                final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.dice);
                mp.start();
                dTwoPlay.startAnimation(rotateDown);

                int twentyPlayerTrow = r.nextInt(20) + 1;
                Animation rotateRevert = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_tree);
                dTwenty.startAnimation(rotateRevert);
                dTwentyNum.setRotation(0);
                dTwentyNum.setText(Integer.toString(twentyPlayerTrow));
            }
        });

        dTwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int twentyPlayerTrow = r.nextInt(20) + 1;
                Animation rotateRevert = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.rotate_tree);
                final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.dice);
                mp.start();
                dTwenty.startAnimation(rotateRevert);

                dTwentyNum.setRotation(0);
                dTwentyNum.setText(Integer.toString(twentyPlayerTrow));
            }
        });
    }

    public void setImageOne(int num) {
        switch (num) {
            case 1:
                dOnePlay.setImageResource(R.drawable.one);
                break;
            case 2:
                dOnePlay.setImageResource(R.drawable.two);
                break;
            case 3:
                dOnePlay.setImageResource(R.drawable.tree);
                break;
            case 4:
                dOnePlay.setImageResource(R.drawable.four);
                break;
            case 5:
                dOnePlay.setImageResource(R.drawable.five);
                break;
            case 6:
                dOnePlay.setImageResource(R.drawable.six);
                break;
        }
    }

    public void setImageTwo(int num) {
        switch (num) {
            case 1:
                dTwoPlay.setImageResource(R.drawable.onet);
                break;
            case 2:
                dTwoPlay.setImageResource(R.drawable.twot);
                break;
            case 3:
                dTwoPlay.setImageResource(R.drawable.treet);
                break;
            case 4:
                dTwoPlay.setImageResource(R.drawable.fourt);
                break;
            case 5:
                dTwoPlay.setImageResource(R.drawable.fivet);
                break;
            case 6:
                dTwoPlay.setImageResource(R.drawable.sixt);
                break;
        }
    }
}