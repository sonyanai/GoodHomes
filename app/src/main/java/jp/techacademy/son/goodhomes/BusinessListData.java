package jp.techacademy.son.goodhomes;


public class BusinessListData {

    private String mUid;
    private String mCompanyName;
    private String mBitmapString;
    private String mKey;



    public String getUid(){
        return mUid;
    }
    public String getCompanyName(){
        return mCompanyName;
    }
    public String getBitmapString(){
        return mBitmapString;
    }
    public String getKey(){
        return mKey;
    }

    public BusinessListData(String uid, String companyName, String bitmapString, String key) {
        mUid = uid;
        mCompanyName = companyName;
        mBitmapString = bitmapString;
        mKey = key;
    }
}

