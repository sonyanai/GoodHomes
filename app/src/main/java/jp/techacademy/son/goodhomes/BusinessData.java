package jp.techacademy.son.goodhomes;

/**
 * Created by taiso on 2018/04/16.
 */

public class BusinessData {

    private String mUid;
    private String mCompanyName;
    private String mAddress;
    private String mCompanyNumber;
    private String mName;
    private String mBitmapString;
    private String mTotalEstimate;
    private String mUnwatchEstimate;
    private String mThisPayment;
    private String mNextPayment;
    private String mTotalEvaluation;
    private String mMoneyEvaluation;
    private String mIndustry;
    private String mPr;
    private String mFlag;



    public String getUid(){
        return mUid;
    }
    public String getCompanyName(){
        return mCompanyName;
    }
    public String getAddress(){
        return mAddress;
    }
    public String getCompanyNumber(){
        return mCompanyNumber;
    }
    public String getName(){
        return mName;
    }
    public String getBitmapString(){
        return mBitmapString;
    }
    public String getTotalEstimate(){
        return mTotalEstimate;
    }
    public String getUnwatchEstimate(){
        return mUnwatchEstimate;
    }
    public String getThisPayment(){
        return mThisPayment;
    }
    public String getNextPayment(){
        return mNextPayment;
    }
    public String getTotalEvaluation(){
        return  mTotalEvaluation;
    }
    public String getMoneyEvaluation(){
        return mMoneyEvaluation;
    }
    public String getIndustry(){
        return mIndustry;
    }
    public String getPr(){
        return mPr;
    }
    public String getFlag(){
        return  mFlag;
    }

    public BusinessData(String uid,String companyName,String address,String companyNumber,String name,String bitmapString,String totalEstimate,String unwatchEstimate,String thisPayment,String nextPayment,String totalEvaluation,String moneyEvaluation,String industry,String pr,String flag) {
        mUid = uid;
        mCompanyName = companyName;
        mAddress = address;
        mCompanyNumber = companyNumber;
        mName = name;
        mBitmapString = bitmapString;
        mTotalEstimate = totalEstimate;
        mUnwatchEstimate = unwatchEstimate;
        mThisPayment = thisPayment;
        mNextPayment = nextPayment;
        mTotalEvaluation = totalEvaluation;
        mMoneyEvaluation = moneyEvaluation;
        mIndustry = industry;
        mPr = pr;
        mFlag = flag;
    }
}

