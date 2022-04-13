package kg.geektech.weathergeektech.data.repositories;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import kg.geektech.weathergeektech.common.RequestState;
import kg.geektech.weathergeektech.data.models.MainResponse;
import kg.geektech.weathergeektech.data.remote.WeatherApi;
import kg.geektech.weathergeektech.room.WeatherDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositories {
    private WeatherApi api;
    private WeatherDao dao;

    @Inject
    public MainRepositories(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }


    public MutableLiveData<RequestState<MainResponse>> getWeather(String city) {
        MutableLiveData<RequestState<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(RequestState.loading());
        api.getApi(city, "89ac1f837c318c7a142986110e0b9c02", "metric").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(RequestState.success(response.body()));
                    dao.insertAll(response.body());
                } else {
                    liveData.setValue(RequestState.error(response.message(), null));

                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(RequestState.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

    public List<MainResponse> getWeatherFromDb() {
        return dao.getAllWeather();
    }

}
