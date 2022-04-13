package kg.geektech.weathergeektech.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.View;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weathergeektech.ConnectionDetector;
import kg.geektech.weathergeektech.R;
import kg.geektech.weathergeektech.base.BaseFragment;
import kg.geektech.weathergeektech.databinding.FragmentWeatherBinding;
import kg.geektech.weathergeektech.data.models.MainResponse;
import kg.geektech.weathergeektech.room.WeatherDao;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherViewModel viewModel;
    private NavController controller;
    private WeatherFragmentArgs args;
    WeatherDao dao;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = WeatherFragmentArgs.fromBundle(getArguments());
        cd = new ConnectionDetector(requireContext().getApplicationContext());
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {
        isInternetPresent = cd.ConnectingToInternet();

        if (isInternetPresent) {
            viewModel.liveData.observe(getViewLifecycleOwner(), mainResponseResource -> {
                switch (mainResponseResource.status) {
                    case SUCCESS: {
                        setData(mainResponseResource.data);
                        binding.progress.setVisibility(View.GONE);
                        break;
                    }
                    case ERROR: {
                        binding.progress.setVisibility(View.GONE);
                        break;
                    }
                    case LOADING: {
                        binding.progress.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            });

        }else {
            setData(viewModel.getWeatherFromDb());
        }

    }


    @Override
    protected void setupViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);

    }

    @Override
    protected void setupListeners() {
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        binding.tvLocation.setOnClickListener(view -> {
            controller.navigate(R.id.action_weatherFragment_to_searchFragment);
        });
    }


    private void setData(MainResponse response) {
        String urlImg = "https://openweathermap.org/img/wn/" + response.getWeather().get(0).getIcon() + ".png";
        String maxTemp = Math.round(response.getMain().getTempMax()) + "°C";
        String wind = (int) Math.round(response.getWind().getSpeed()) + " km/h";
        String minTemp = (int) Math.round(response.getMain().getTempMin()) + "°C";
        String humidity = response.getMain().getHumidity() + "%";
        String barometer = response.getMain().getPressure() + "mBar";
        String mainWeather = response.getWeather().get(0).getMain();

        String tempNow = String.valueOf((int) Math.round(response.getMain().getTemp()));

        Glide.with(requireActivity()).load(urlImg).into(binding.ivWeather);
        binding.tvTempVariationUp.setText(maxTemp);
        binding.tvTempVariationDown.setText(minTemp);
        binding.tvWind.setText(wind);
        binding.tvHumidity.setText(humidity);
        binding.tvTemperature.setText(tempNow);
        binding.tvBarometer.setText(barometer);
        binding.tvWeather.setText(mainWeather);

        binding.tvLocation.setText(response.getName());
        binding.tvSunrise.setText(getDate(response.getSys().getSunrise(), "hh:mm a")); // a-взвращает AM
        binding.tvSunset.setText(getDate(response.getSys().getSunset(), "hh:mm a"));
        binding.tvDaytime.setText(getDate(response.getDt(), "hh:mm"));

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        binding.tvDate.setText(dateText);
    }

    public static String getDate(Integer milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    protected void callRequests() {
        if(args.getCity() != null) {
            viewModel.getWeather(args.getCity());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar uh = Calendar.getInstance();
        int timeOfDay = uh.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 6 && timeOfDay < 20) {
            binding.ivDayNight.setImageResource(R.drawable.ic_graphic);
        } else {
            binding.ivDayNight.setImageResource(R.drawable.ic_graphic__1_);
        }
    }
}