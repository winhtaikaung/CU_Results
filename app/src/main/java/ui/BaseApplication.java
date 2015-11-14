package ui;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.curesults.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import model.Result;
import provider.ResultRealmHelper;

/**
 * Created by winhtaikaung on 11/14/15.
 */
public class BaseApplication extends Application {
    List<Result> resultlist = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();

    }


}
