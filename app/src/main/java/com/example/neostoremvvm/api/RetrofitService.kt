package com.example.neostoremvvm.api

import com.example.neostoremvvm.dataclass.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {

    @GET("products/getList?")
    fun productList(
        @Query("product_category_id") product_category_id:String
    ):Call<ProductList>

    @GET("products/getDetail")
    fun productDetail(
        @Query("product_id") product_id: String
    ):Call<ProductDetail>

    @FormUrlEncoded
    @POST("addToCart")
    fun addToCart(
        @Header("access_token") access_token:String,
        @Field("product_id") product_id:Int?,
        @Field("quantity") quantity:Int?
    ):Call<AddToCartResponse>

     @FormUrlEncoded
     @POST("users/register")
     fun createUser(
         @Field("first_name") first_name : String,
         @Field("last_name") last_name:String,
         @Field("email") email : String,
         @Field("password") password : String,
         @Field("confirm_password") confirm_password:String,
         @Field("gender") gender:String,
         @Field("phone_no") phone_no: Number
     ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("users/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password:String
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("users/forgot")
    fun forgotPassword(
        @Field("email") email: String
    ):Call<ForgotPasswordResponse>

    @FormUrlEncoded
    @POST("products/setRating")
    fun ratingProduct(
        @Field("product_id") product_id:String,
        @Field("rating") rating:Int?
    ):Call<RatingResponse>

    @FormUrlEncoded
    @POST("users/update")
    fun updateProfile(
        @Header("access_token") access_token : String,
        @Field("first_name") first_name :String,
        @Field("last_name") last_name: String,
        @Field("email") email:String,
        @Field("dob") dob: String,
        @Field("phone_no") phone_no : String,
        @Field("profile_pic") profile_pic:String
    ):Call<UpdateProfileClass>


    @FormUrlEncoded
    @POST("users/change")
    fun changePassword(
        @Header("access_token") access_token : String,
        @Field("old_password") old_password:String,
        @Field("password") password:String,
        @Field("confirm_password") confirm_password: String
    ):Call<UpdateProfileClass>

    @GET("users/getUserData")
    fun fetchAccountDetail(
        @Header("access_token") access_token : String,
    ):Call<FetchAccountDetail>

    @GET("orderList")
    fun orderList(
        @Header("access_token") access_token:String
    ):Call<OrderListResponse>


    @GET("orderDetail")
    fun orderDeatilList(
        @Header("access_token") access_token:String,
        @Query("order_id") order_id:Int?
    ):Call<OrderDetailResponse>

    @GET("cart")
    fun cartList(
        @Header("access_token") access_token:String
    ):Call<CartListResponse>

    @FormUrlEncoded
    @POST("deleteCart")
    fun deleteCartItem(
        @Header("access_token") access_token:String,
        @Field("product_id") product_id:Int?
    ):Call<CartDeleteResponse>

    @FormUrlEncoded
    @POST("editCart")
    fun editCartItem(
        @Header("access_token") access_token:String,
        @Field("product_id") product_id:Int?,
        @Field("quantity") quantity:Int?
    ):Call<CartEditResponse>

    @FormUrlEncoded
    @POST("order")
    fun placeOrder(
        @Header("access_token") access_token:String,
        @Field("address") address: String
    ):Call<PlaceOrderResponse>

    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://staging.php-dev.in:8844/trainingapp/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}