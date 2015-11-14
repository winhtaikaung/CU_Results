package provider;

/**
 * Created by winhtaikaung on 10/15/15.
 */
import android.content.Context;



import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;
import model.Result;


/**
 * Created by winhtaikaung on 9/19/15.
 */
public class ResultRealmHelper {

    private static ResultRealmHelper
            realmHelper;
    private Context mContext;
    private Realm mRealm;

    public ResultRealmHelper(Context _context) {
        mContext = _context;
        mRealm = Realm.getInstance(_context);


    }

    public static synchronized ResultRealmHelper getInstance(Context _context) {
        if (realmHelper == null) {
            realmHelper = new ResultRealmHelper(_context);
        }
        return realmHelper;
    }

    /**
     * It will update when the item was exist
     * else it will insert Party when
     *
     * @param party
     */
    public void UpsertTownship(Result party) {
        try {
            mRealm.beginTransaction();

            mRealm.copyToRealmOrUpdate(party);
            mRealm.commitTransaction();

        } catch (RealmException re) {
            re.printStackTrace();
        }
    }

    void DeleteParty(Result party) {
        try {
            mRealm.beginTransaction();

            mRealm.copyToRealmOrUpdate(party);
            mRealm.commitTransaction();

        } catch (RealmException re) {
            re.printStackTrace();
        }
    }

    public List<Result> getTownShipList() {
        List Partylist = new ArrayList();
        try {
            RealmResults<Result> results = mRealm.where(Result.class).findAll();

            Partylist = results;


        } catch (RealmException re) {
            re.printStackTrace();
        }
        return Partylist;
    }

    /**
     * Get All child Township By using District Code
     * @param dPcode District Code
     * @return List<GeoTownship></>
     */
    public List<Result> getChildTownshipList(String dPcode) {
        List Partylist = new ArrayList();
        try {
            RealmResults<Result> results = mRealm.where(Result.class)
                    .equalTo("dPcode", dPcode).findAll();

            Partylist = results;


        } catch (RealmException re) {
            re.printStackTrace();
        }
        return Partylist;
    }

    public Result getStudentByRollnumber(String ID) {
        Result result = new Result();

        try {
            result = mRealm.where(Result.class).equalTo("roll_no", ID).findFirst();
        } catch (RealmException re) {
            re.printStackTrace();
        }

        return result;
    }


}
