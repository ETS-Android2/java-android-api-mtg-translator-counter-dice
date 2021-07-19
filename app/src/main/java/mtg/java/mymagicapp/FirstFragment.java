package mtg.java.mymagicapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtg.java.mymagicapp.databinding.FragmentFirstBinding;
import mtg.java.mymagicapp.mtgClass.ApiCall;
import mtg.java.mymagicapp.mtgClass.AutoSuggestAdapter;
import mtg.java.mymagicapp.mtgClass.RoundedCornersTransformation;

public class FirstFragment extends Fragment {

    static Map<String, String> nameCardMap = new HashMap<String, String>();
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    private Handler handler;
    private AutoSuggestAdapter autoSuggestAdapter;
    private static String ImageURL;
    private static String DoubleImageURL;
    public static String cardname;
    public static String locale = "ru";
    public static String butlocale = "ru";
    private static String oracletext;
    private static String typeline;
    static String firstUrl = "https://api.scryfall.com/cards/named?fuzzy=";
    static RequestQueue mRequestQueue;
    private static FragmentFirstBinding binding;
    static HashMap<String, String> legals = new HashMap<String, String>();

    public void getCard(String url) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url + cardname, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject image = response.getJSONObject("image_uris");
                    JSONObject legalObject = response.getJSONObject("legalities");
                    legals = new Gson().fromJson(legalObject.toString(), HashMap.class);

                    DoubleImageURL = image.getString("large");
                    typeline = response.getString("type_line");
                    oracletext = response.getString("oracle_text");
                    String collectorNumber = response.getString("collector_number");
                    String setName = response.getString("set");
                    setUrl(collectorNumber, setName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    private void getImageRu(String url) {
        final TextView selectedText = binding.textviewFirst;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject image = response.getJSONObject("image_uris");
                    JSONObject legalObject = response.getJSONObject("legalities");
                    legals = new Gson().fromJson(legalObject.toString(), HashMap.class);

                    ImageURL = image.getString("large");
                    typeline = response.getString("printed_type_line");
                    oracletext = response.getString("printed_text");
                    selectedText.setText("Yeah, we find " + locale + " card");
                    setImage(ImageURL,typeline, oracletext);
                } catch (JSONException e) {
                    e.printStackTrace();
                    setImage(DoubleImageURL, typeline, oracletext);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                setImage(DoubleImageURL, typeline, oracletext);
                selectedText.setText("Sorry, no find " + locale + " card");
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setUrl(String num, String set) {
        String secondUrl = "https://api.scryfall.com/cards/" + set + "/" + num + "/" + locale;
        getImageRu(secondUrl);
    }

    private void setImage(String img, String type, String oracle) {
        final TextView typelineText = binding.typeLine;
        final TextView oracleText = binding.oracleText;
        final ImageView imageView = binding.imageView;
        final ChipGroup chipsPrograms = binding.chipsPrograms;
        final TextView legalStatus = binding.legalStatus;

        final int radius = 20;
        final int margin = 20;

        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        Picasso.get().load(img).transform(transformation).into(imageView);
        typelineText.setText(type);
        legalStatus.setText("Legal status");
        oracleText.setText(oracle);

        setLegal(legals, chipsPrograms);
    }

    private void setLegal(HashMap<String, String> legals, ChipGroup chipsPrograms) {
        for(HashMap.Entry<String, String> item : legals.entrySet()){
            if (item.getValue().equals("legal")) {
                String formatValue = item.getKey();
                Chip lChip = new Chip(getActivity());
                lChip.setText(formatValue);
                lChip.setTextColor(getResources().getColor(R.color.white));
                lChip.setChipBackgroundColor(getResources().getColorStateList(R.color.forest));
                chipsPrograms.addView(lChip, chipsPrograms.getChildCount());
            }
        }
        legals.clear();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRequestQueue = Volley.newRequestQueue(getActivity());

        final Button butOne = binding.cardLang;
        final Button butTwo = binding.searchHistory;

        final AutoCompleteTextView autoCompleteTextView = binding.autoCompleteEditText;
        final ChipGroup chipsPrograms = binding.chipsPrograms;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String langlocale = preferences.getString("setLang", "en");
        String langbutlocale = preferences.getString("setButLang", "Set language1");
        locale = langlocale;
        butOne.setText(langbutlocale);

        autoSuggestAdapter = new AutoSuggestAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(autoSuggestAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cardname = autoSuggestAdapter.getObject(position);
                nameCardMap.put(cardname, cardname);
                getCard(firstUrl + cardname);
                chipsPrograms.removeAllViews();
                ((MainActivity)getActivity()).closeKeyboard();
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE, AUTO_COMPLETE_DELAY);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TRIGGER_AUTO_COMPLETE) {
                    if (!TextUtils.isEmpty(autoCompleteTextView.getText())) {
                        makeApiCall(autoCompleteTextView.getText().toString());
                    }
                }
                return false;
            }
        });

        butOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"Russian", "English", "Spanish", "French", "German", "Italian", "Portuguese", "Japanese", "Korean", "Simplified Chinese", "Traditional Chinese"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select you language:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item) {
                            case 0:
                                FirstFragment.locale = "ru";
                                getCard(firstUrl);
                                break;
                            case 1:
                                FirstFragment.locale = "en";
                                getCard(firstUrl);
                                break;
                            case 2:
                                FirstFragment.locale = "es";
                                getCard(firstUrl);
                                break;
                            case 3:
                                FirstFragment.locale = "fr";
                                getCard(firstUrl);
                                break;
                            case 4:
                                FirstFragment.locale = "de";
                                getCard(firstUrl);
                                break;
                            case 5:
                                FirstFragment.locale = "it";
                                getCard(firstUrl);
                                break;
                            case 6:
                                FirstFragment.locale = "pt";
                                getCard(firstUrl);
                                break;
                            case 7:
                                FirstFragment.locale = "ja";
                                getCard(firstUrl);
                                break;
                            case 8:
                                FirstFragment.locale = "ko";
                                getCard(firstUrl);
                                break;
                            case 9:
                                FirstFragment.locale = "zhs";
                                getCard(firstUrl);
                                break;
                            case 10:
                                FirstFragment.locale = "zht";
                                getCard(firstUrl);
                                break;
                        }
                        dialog.dismiss();
                        butlocale = (String) items[item];
                        butOne.setText(butlocale);
                        saveLang();
                        chipsPrograms.removeAllViews();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        butTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] objectArray = (FirstFragment.nameCardMap).keySet().toArray(new String[0]);
                AlertDialog.Builder buildernew = new AlertDialog.Builder(getActivity());
                buildernew.setTitle("You search history:");
                buildernew.setItems((CharSequence[]) objectArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        FirstFragment.cardname = objectArray[item];
                        getCard(firstUrl);
                        dialog.dismiss();
                        chipsPrograms.removeAllViews();
                    }
                });
                AlertDialog alertnew = buildernew.create();
                alertnew.show();
            }
        });
    }
    private static FragmentActivity myContext;


    private void addChip(String pItem, ChipGroup pChipGroup) {

    }


    private void makeApiCall(String text) {
        ApiCall.make(getActivity(), text, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<String> stringList = new ArrayList<>();
                try {
                    JSONObject responseObject = new JSONObject(response);
                    JSONArray array = responseObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        stringList.add(array.get(i).toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                autoSuggestAdapter.setData(stringList);
                autoSuggestAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    private void saveLang() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("setLang", locale);
        editor.putString("setButLang", butlocale);
        editor.apply();
    }

    private void saveCard() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        for (String s : nameCardMap.keySet()) {
            editor.putString(s, nameCardMap.get(s));
        }
        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}