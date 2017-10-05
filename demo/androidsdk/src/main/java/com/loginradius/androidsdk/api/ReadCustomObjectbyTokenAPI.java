package com.loginradius.androidsdk.api;


import com.loginradius.androidsdk.handler.ApiInterface;
import com.loginradius.androidsdk.handler.AsyncHandler;
import com.loginradius.androidsdk.handler.ExceptionResponse;
import com.loginradius.androidsdk.handler.RestRequest;
import com.loginradius.androidsdk.resource.Endpoint;
import com.loginradius.androidsdk.response.customobject.ReadCustomObject;
import com.loginradius.androidsdk.response.login.LoginParams;
import com.loginradius.androidsdk.response.lrAccessToken;

import java.util.HashMap;
import java.util.LinkedHashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by loginradius on 23-Nov-16.
 */

public class ReadCustomObjectbyTokenAPI {


    public void getResponse(LoginParams value, lrAccessToken token,final AsyncHandler<ReadCustomObject> handler)
    {
        HashMap<String,String> params = new LinkedHashMap<>();
        params.put("apikey", value.apikey);
        params.put("access_token",token.access_token);
        params.put("objectname",value.objectname);

        ApiInterface apiService = RestRequest.getClient().create(ApiInterface.class);
        apiService.getReadCustomobjectByToken(Endpoint.API_V2_CUSTOMOBJECT,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ReadCustomObject>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        ExceptionResponse exceptionResponse = ExceptionResponse.HandleException(e);
                        handler.onFailure(exceptionResponse.t, exceptionResponse.message);
                    }

                    @Override
                    public void onNext(ReadCustomObject response) {
                        handler.onSuccess(response);
                    }

                });
    }

    public void getResponse(LoginParams value, lrAccessToken token, String fields[], final AsyncHandler<ReadCustomObject> handler)
    {
        HashMap<String,String> params = new LinkedHashMap<>();
        params.put("apikey", value.apikey);
        params.put("access_token",token.access_token);
        params.put("objectname",value.objectname);

        String strFields = null;
        if(fields!=null && fields.length>0){
            strFields = "";
            for(int i=0;i<fields.length;i++){
                if(i == (fields.length-1)){
                    strFields = strFields + fields[i];
                }else{
                    strFields = strFields + fields[i] + ",";
                }
            }
        }

        if(strFields!=null){
            params.put("fields",strFields);
        }

        ApiInterface apiService = RestRequest.getClient().create(ApiInterface.class);
        apiService.getReadCustomobjectByToken(Endpoint.API_V2_CUSTOMOBJECT,params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ReadCustomObject>() {
                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {
                        ExceptionResponse exceptionResponse = ExceptionResponse.HandleException(e);
                        handler.onFailure(exceptionResponse.t, exceptionResponse.message);
                    }

                    @Override
                    public void onNext(ReadCustomObject response) {
                        handler.onSuccess(response);
                    }

                });
    }
}
