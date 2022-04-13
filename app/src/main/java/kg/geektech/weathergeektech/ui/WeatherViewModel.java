package kg.geektech.weathergeektech.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weathergeektech.common.RequestState;
import kg.geektech.weathergeektech.data.models.MainResponse;
import kg.geektech.weathergeektech.data.repositories.MainRepositories;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public MainRepositories repository;
    public LiveData<RequestState<MainResponse>> liveData;

    @Inject
    public WeatherViewModel(MainRepositories repository) {
        this.repository = repository;
    }



    public void getWeather(String city) {
        liveData = repository.getWeather(city);
    }


    public MainResponse getWeatherFromDb() {
        List<MainResponse> list = repository.getWeatherFromDb();
        return list.get(list.size() - 1);
    }

}
