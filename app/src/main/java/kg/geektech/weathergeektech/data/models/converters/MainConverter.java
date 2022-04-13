package kg.geektech.weathergeektech.data.models.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weathergeektech.data.models.Main;

public class MainConverter {
    @TypeConverter
    public String fromMainString(Main main) {
        if (main == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {
        }.getType();
        return gson.toJson(main, type);
    }

    @TypeConverter
    public Main fromMainString(String mainString) {
        if (mainString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {
        }.getType();
        return gson.fromJson(mainString, type);
    }
}
