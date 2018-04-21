package jp.techacademy.son.goodhomes;

/**
 * Created by taiso on 2018/04/20.
 */

public class CustomerListData {

    private String mUid;
    private String mName;
    private String mPlace;
    private String mKey1;
    private String mKey2;



    public String getUid(){
        return mUid;
    }
    public String getName(){
        return mName;
    }
    public String getPlace(){
        return mPlace;
    }
    public String getKey1(){
        return mKey1;
    }
    public String getKey2(){
        return mKey2;
    }

    public CustomerListData(String uid, String name, String place, String key1,String key2) {
        mUid = uid;
        mName = name;
        mPlace = place;
        mKey1 = key1;
        mKey2 = key2;
    }
}

