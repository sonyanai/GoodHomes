package jp.techacademy.son.goodhomes;

/**
 * Created by taiso on 2018/04/16.
 */

public class CustomerData {

    private String mUid;
    private String mName;
    private String mPostalCode;
    private String mAgeBuild;
    private String mForm;
    private String mOtherForm;
    private String mPro;
    private String mOtherPro;
    private String mPlace;
    private String mOtherPlace;
    private String mBudget;
    private String mAge;
    private String mSex;
    private String mEstimate;
    private String mRequestEstimate;
    private String mRequest;
    private String mFlag;





    public String getUid(){
        return mUid;
    }
    public String getName(){
        return mName;
    }
    public String getPostalCode(){
        return mPostalCode;
    }
    public String getAgeBuild(){
        return mAgeBuild;
    }
    public String getForm(){
        return mForm;
    }
    public String getOtherForm(){
        return mOtherForm;
    }
    public String getPro(){
        return mPro;
    }
    public String getOtherPro(){
        return mOtherPro;
    }
    public String getPlace(){
        return mPlace;
    }
    public String getOtherPlace(){
        return mOtherPlace;
    }
    public String getBudget(){
        return mBudget;
    }
    public String getAge(){
        return mAge;
    }
    public String getSex(){
        return mSex;
    }
    public String getEstimate(){
        return mEstimate;
    }
    public String getRequestEstimate(){
        return mRequestEstimate;
    }
    public String getRequest(){
        return mRequest;
    }
    public String getFlag(){
        return mFlag;
    }

    public CustomerData(String uid,String name,String postalCode,String ageBuild,String form,String otherForm,String pro,String otherPro,String place,String otherPlace,String budget,String age,String sex,String estimate,String requestEstimate,String request,String flag) {
        mUid = uid;
        mName = name;
        mPostalCode = postalCode;
        mAgeBuild =ageBuild;
        mForm = form;
        mOtherForm = otherForm;
        mPro = pro;
        mOtherPro = otherPro;
        mPlace = place;
        mOtherPlace = otherPlace;
        mBudget = budget;
        mAge = age;
        mSex = sex;
        mEstimate = estimate;
        mRequestEstimate = requestEstimate;
        mRequest = request;
        mFlag = flag;
    }
}

