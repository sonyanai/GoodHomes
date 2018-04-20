package jp.techacademy.son.goodhomes;

/**
 * Created by taiso on 2018/04/20.
 */

public class CustomerListData {

    private String mUid;
    private String mName;
    private String mPlace;
    private String mKey;



    public String getUid(){
        return mUid;
    }
    public String getName(){
        return mName;
    }
    public String getPlace(){
        return mPlace;
    }
    public String getKey(){
        return mKey;
    }

    public CustomerListData(String uid, String name, String place, String key) {
        mUid = uid;
        mName = name;
        mPlace = place;
        mKey = key;
    }
}

