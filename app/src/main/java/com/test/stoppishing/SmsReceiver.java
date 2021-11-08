package com.test.stoppishing;

import static android.content.Context.TELEPHONY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;

//SMS Receiver
public class SmsReceiver extends BroadcastReceiver {
    private String logTag = "[SMSReceiver]";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(logTag, "onReceive is called");

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            Log.i(logTag, "SMS is received");

            //Bundle 널 체크
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                Log.e(logTag, "Bundle is NULL");

                return;
            }

            //pdu 객체 널 체크
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            if (pdusObj == null) {
                Log.e(logTag, "pdusObj is NULL");
                return;
            }

            //message 처리
            SmsMessage[] smsMessages = new SmsMessage[pdusObj.length];

            //log msg
            //logMsgContents(pdusObj, smsMessages);
            JSONObject jsonObj = makeJSONObj(pdusObj, smsMessages);
            Log.i(logTag, "JSON String = " + jsonObj.toString());

            sendJSONObj(context, jsonObj);


        }

    }

    public void logMsgContents(Object[] pdusObj, SmsMessage[] smsMessages) {
        for (int i = 0; i < pdusObj.length; i++) {
            smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

            //Message contents
            Log.i(logTag, "DisplayMessageBody : "
                    + smsMessages[i].getDisplayMessageBody());
            //phone number
            Log.i(logTag, "OriginatingAddress : "
                    + smsMessages[i].getOriginatingAddress());
            //received time
            Log.i(logTag, "TimestampMillis : "
                    + smsMessages[i].getTimestampMillis());
        }
    }

    public JSONObject makeJSONObj(Object[] pdusObj, SmsMessage[] smsMessages) {
        JSONObject jsonObj = new JSONObject();

        for (int i = 0; i < pdusObj.length; i++) {
            smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

            try {
                jsonObj.put("MessageBody", smsMessages[i].getDisplayMessageBody());
                jsonObj.put("From", smsMessages[i].getOriginatingAddress());
                jsonObj.put("TimeStamp", smsMessages[i].getTimestampMillis());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return jsonObj;
    }

    public String getMyPhoneNumber(Context context) {
        String phoneNumber = new String();




        return phoneNumber;
    }

    public void sendJSONObj(Context context, JSONObject JsonObj){
        RequestQueue requestQueue;
        String url = "http://SERVERURL?verify=";
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        StringRequest stringReq = new StringRequest(Request.Method.GET,
                url+JsonObj.toString(),
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String res){
                        Log.i(logTag, "Send Request Success");
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e(logTag, "VolleyError : " + error);
                    }
                });
    }

//onResponse Listener
/*
    @Override
    public void onResponse(String res){
        Log.i(logTag, "Send Request Success");
    }
*/


    //ErrorListener
/*
    @Override
    public void onErrorResponse(VolleyError error){
        Log.e(logTag, "VolleyError : " + error);
    }
 */

}
