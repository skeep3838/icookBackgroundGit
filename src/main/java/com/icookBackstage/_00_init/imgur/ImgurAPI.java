package com.icookBackstage._00_init.imgur;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

//使用retrofit API來快速建立 與imgur API 的RESTful請求
public interface ImgurAPI {
	
	//imgurAPI連線的基底
    String SERVER = "https://api.imgur.com";
    //帳號授權ID
    public static final String AUTH = "cbea1874b2decf3";
    
    //retrofit的Header與POST設定
    @Headers("Authorization: Client-ID " + AUTH)
    @POST("/3/upload")
    Call<ImageResponse> postImage(
            @Body RequestBody image
    );
    
}