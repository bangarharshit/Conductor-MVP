package com.harshitbangar.conductormvp;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
final class AppModule {

  private static final String NOAA_API_BASE_URL = "https://tidesandcurrents.noaa.gov/";

  @Provides
  @Singleton
  NoaaApi provideNoaaApi(Retrofit retrofit) {
    return retrofit.create(NoaaApi.class);
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(OkHttpClient httpClient) {
    return new Retrofit.Builder()
        .baseUrl(NOAA_API_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(JacksonConverterFactory.create())
        .client(httpClient)
        .build();
  }

  @Provides
  @Singleton
  OkHttpClient provideHttpClient() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return new OkHttpClient.Builder().addInterceptor(interceptor).build();
  }

}
