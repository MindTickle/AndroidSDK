package com.mindtickle.integrations.android_sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.mindtickle.integrations.android_sdk.exceptions.MindTickleNotInitializedException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Created by himanshugupta on 15/10/16.
 */

public class MindTickle {
    private static String _domain;
    private static String _secret;
    private static String _email;
    private static Context _context;
    private static String _branch_url;

    private static boolean _is_initialized = false;

    public static void initialize(Context mContext, String domain, String secret) {
        Branch.getAutoInstance(mContext);
        _context = mContext;
        _domain = domain;
        _secret = secret;
        _is_initialized = true;
    }

    public static void initialize(Context mContext, String domain, String secret, String email) {
        initialize(mContext,domain,secret);
        _email=email;
    }


    private static String getJWTToken(String email) throws JSONException, MindTickleNotInitializedException{
        if(_secret==null||_secret.isEmpty()||_domain==null||_domain.isEmpty()) throw new MindTickleNotInitializedException();
        JSONObject payLoad = new JSONObject();
        JSONObject emailObject = new JSONObject();
        emailObject.put("email",email);
        payLoad.put("identifier",emailObject);
        payLoad.put("domain",_domain);
        return Jwts.builder().setPayload(payLoad.toString()).signWith(SignatureAlgorithm.HS512,_secret).compact();
    }
    public static void setUserEmail(String email) throws JSONException, MindTickleNotInitializedException{
        _email = email;
        getBranchLink();
    }

    private static void getBranchLink() throws JSONException, MindTickleNotInitializedException{
        BranchUniversalObject branchUniversalObject = new BranchUniversalObject()
                .addContentMetadata("access_token", getJWTToken(_email))
                .addContentMetadata("domain", _domain);
        LinkProperties linkProperties = new LinkProperties();
        branchUniversalObject.generateShortUrl(_context, linkProperties, new Branch.BranchLinkCreateListener() {
            @Override
            public void onLinkCreate(String url, BranchError error) {
                if (error == null) {
                    _branch_url = url;
                }
            }
        });
    }

    private static String get_branch_url(){
        return _branch_url;
    }

    public static void openMindTickle() throws MalformedURLException, IOException{
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(_branch_url));
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(browserIntent);
    }

//    public static class ClickOnBranch extends AsyncTask<Void,Void,Void> {
//
//        private String link;
//
//        ClickOnBranch(String link){
//            this.link = link;
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            try {
//                URL url = new URL(link);
//                HttpsURLConnection httpClient = (HttpsURLConnection)url.openConnection();
//                print_content(httpClient);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        private void print_content(HttpsURLConnection con){
//            if(con!=null){
//
//                try {
//
//                    System.out.println("****** Content of the URL ********");
//                    BufferedReader br =
//                            new BufferedReader(
//                                    new InputStreamReader(con.getInputStream()));
//
//                    while ((br.readLine()) != null){
//                    }
//                    br.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//
//    }

}
