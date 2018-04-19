package jp.techacademy.son.goodhomes;


public class BusinessListData {

    private String mUid;
    private String mCompanyName;
    private String mBitmapString;



    public String getUid(){
        return mUid;
    }
    public String getCompanyName(){
        return mCompanyName;
    }
    public String getBitmapString(){
        return mBitmapString;
    }

    public BusinessListData(String uid, String companyName, String bitmapString) {
        mUid = uid;
        mCompanyName = companyName;
        mBitmapString = bitmapString;
    }
}

