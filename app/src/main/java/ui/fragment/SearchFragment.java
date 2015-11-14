package ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.curesults.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import model.Result;
import provider.ResultRealmHelper;
import ui.adapter.MySinneradapter;
import validator.RequiredValidator;

/**
 * Created by winhtaikaung on 11/14/15.
 */
public class SearchFragment extends Fragment {

  MaterialEditText edit_rollnumber;
    AppCompatSpinner spinner_CT_CS;
    AppCompatSpinner  spinner_year;
    MaterialDialog progress_dialog;
    TextView btn_search;
    Toolbar mToolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_search,container,false);

        mToolbar=(Toolbar)v.findViewById(R.id.toolbar);

        edit_rollnumber=(MaterialEditText)v.findViewById(R.id.edit_rollnumber);
        edit_rollnumber.addValidator(new RequiredValidator(""))
        spinner_CT_CS=(AppCompatSpinner)v.findViewById(R.id.spinner_CT_CS);
        spinner_year=(AppCompatSpinner) v.findViewById(R.id.spinner_year);
        btn_search=(TextView) v.findViewById(R.id.btn_search);
        mToolbar.setTitle(getActivity().getResources().getString(R.string.app_name));
        List<String> lst_year= Arrays.asList(getActivity().getResources().getStringArray(R.array.arr_years));
        List<String> lst_specialication= Arrays.asList(getActivity().getResources().getStringArray(R.array.arr_CT_CS));
        MySinneradapter year_spinner_adapter= new MySinneradapter(getActivity(),android.R.layout.simple_spinner_item,lst_year);
        MySinneradapter ct_cu_spinner_adapter= new MySinneradapter(getActivity(),android.R.layout.simple_spinner_item,lst_specialication);

        spinner_CT_CS.setAdapter(ct_cu_spinner_adapter);
        spinner_year.setAdapter(year_spinner_adapter);
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity())
                .title("Loading...")
                .autoDismiss(false)
                .customView(R.layout.loading, false);

        progress_dialog=builder.build();

        if(ResultRealmHelper.getInstance(getContext()).getTownShipList().size()==0){
            new AsyncdataBindResults().execute();
        }

        return v;
    }

    class AsyncdataBindResults extends AsyncTask<String, String, List<Result>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity(), getActivity().getString(R.string.creating_db), Toast.LENGTH_LONG).show();
            progress_dialog.show();



        }

        @Override
        protected List<Result> doInBackground(String... params) {
            Object obj = null;
            List<Result> parsedItemList = new ArrayList<Result>();
            Realm mRealm = Realm.getInstance(getActivity());
            try {
                String t = getResources().getString(R.string.result_json);
                JSONObject listobj = new JSONObject(t);

                if (!listobj.isNull("data")) {
                    JSONArray listArry = listobj.getJSONArray("data");

                    int i = 0;
                    while (i < listArry.length()) {
                        ObjectMapper mapper = new ObjectMapper();
                        Result partyItem = new Result();

                        try {
                            obj = mapper.readValue(listArry.getString(i), Result.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        partyItem = (Result) obj;
                        parsedItemList.add(partyItem);

                        try {
                            mRealm.beginTransaction();

                            mRealm.copyToRealmOrUpdate(partyItem);
                            mRealm.commitTransaction();

                        } catch (RealmException re) {
                            re.printStackTrace();
                        }
                        //resultlist.add(partyItem);


                        i++;
                        Log.w("TOTAL_COUNT", String.valueOf(i));
                        progress_dialog.setTitle("Loading"+i+"of"+String.valueOf(listArry.length()));
                    }

                    Log.w("parsing completed", "COmpleted!");
                }


            } catch (JSONException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Result> s) {

            progress_dialog.dismiss();
            Toast.makeText(getActivity(),getActivity().getString(R.string.creating_db_complete),Toast.LENGTH_LONG).show();
            //GeoTownshipRealmHelper.getInstance(getApplicationContext()).UpsertTownship(township);
            //Dismiss Progress and start activity
        }
    }
}
