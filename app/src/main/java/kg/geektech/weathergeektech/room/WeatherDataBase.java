package kg.geektech.weathergeektech.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weathergeektech.data.models.MainResponse;
import kg.geektech.weathergeektech.data.models.converters.CloudConverter;
import kg.geektech.weathergeektech.data.models.converters.CoordConverter;
import kg.geektech.weathergeektech.data.models.converters.MainConverter;
import kg.geektech.weathergeektech.data.models.converters.SysConverter;
import kg.geektech.weathergeektech.data.models.converters.WeatherConverter;
import kg.geektech.weathergeektech.data.models.converters.WindConverter;

@Database(entities = {MainResponse.class}, version = 1,exportSchema = false)
@TypeConverters({MainConverter.class, CloudConverter.class, CoordConverter.class,
        SysConverter.class, WeatherConverter.class, WindConverter.class})
public abstract class WeatherDataBase extends RoomDatabase {
    public abstract WeatherDao weatherDao();

}
