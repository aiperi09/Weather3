package kg.geektech.weathergeektech.ui;

import kg.geektech.weathergeektech.base.BaseFragment;
import kg.geektech.weathergeektech.databinding.FragmentSearchBinding;


public class SearchFragment extends BaseFragment<FragmentSearchBinding> {

    @Override
    protected FragmentSearchBinding bind() {
        return FragmentSearchBinding.inflate(getLayoutInflater());
    }



    @Override
    protected void setupViews() {

    }

    @Override
    protected void setupListeners() {
        binding.btn.setOnClickListener(view -> {
            String city = binding.etText.getText().toString();
            SearchFragmentDirections.ActionSearchFragmentToWeatherFragment action =
                    SearchFragmentDirections.actionSearchFragmentToWeatherFragment();
            action.setCity(city);
            controller.navigate(action);
        });


    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void callRequests() {

    }
}