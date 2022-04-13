package kg.geektech.weathergeektech.di;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

import kg.geektech.weathergeektech.data.remote.WeatherApi;
import kg.geektech.weathergeektech.data.repositories.MainRepositories;
import kg.geektech.weathergeektech.room.WeatherDao;
import kg.geektech.weathergeektech.room.WeatherDataBase;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {
    @Provides
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }

    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public static WeatherApi provideApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    @Singleton
    public static MainRepositories provideMainRepository(WeatherApi api,WeatherDao dao){
        return new MainRepositories(api,dao);
    }

    @Provides
    public static WeatherDao provideDao(WeatherDataBase database) {
        return database.weatherDao();
    }

    @Provides
    public static WeatherDataBase provideDataBase(@ApplicationContext Context context){
         return Room.databaseBuilder(context,
                WeatherDataBase.class, "WeatherDataBase")
                 .allowMainThreadQueries()
                 .build();
    }


}
