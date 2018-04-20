package jp.techacademy.son.goodhomes;


public class BusinessListData {

    private String mUid;
    private String mCompanyName;
    private String mBitmapString;
    private String mIndustry;
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
    public String getIndustry(){
        return mIndustry;
    }
    public String getKey(){
        return mKey;
    }

    public BusinessListData(String uid, String companyName, String bitmapString, String industry, String key) {
        mUid = uid;
        mCompanyName = companyName;
        mBitmapString = bitmapString;
        mIndustry = industry;
        mKey = key;
    }
}

