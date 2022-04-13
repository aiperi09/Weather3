package kg.geektech.weathergeektech.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kg.geektech.weathergeektech.data.models.MainResponse;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM mainresponse")
    List<MainResponse> getAllWeather();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MainResponse weather);

}
