package com.bicos.myopendiary.common;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by raehyeong.park on 2017. 3. 10..
 */

public class FirebaseWrapper {

    public static DatabaseReference getDiaryReference(){
        return FirebaseDatabase.getInstance().getReference().child(Constants.REF_DIARY);
    }

    public static DatabaseReference getDiaryReference(String uid, String date) {
        return FirebaseDatabase.getInstance().getReference().child(Constants.REF_DIARY).child(uid).child(date);
    }
}
