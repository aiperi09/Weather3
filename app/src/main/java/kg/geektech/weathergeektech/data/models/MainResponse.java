
package kg.geektech.weathergeektech.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kg.geektech.weathergeektech.data.models.converters.CloudConverter;
import kg.geektech.weathergeektech.data.models.converters.CoordConverter;
import kg.geektech.weathergeektech.data.models.converters.MainConverter;
import kg.geektech.weathergeektech.data.models.converters.SysConverter;
import kg.geektech.weathergeektech.data.models.converters.WeatherConverter;


@Entity
public class MainResponse {


    @SerializedName("coord")
    @Expose
    @TypeConverters({CoordConverter.class})
    private kg.geektech.weathergeektech.data.models.Coord coord;
    @SerializedName("weather")
    @Expose
    @TypeConverters({WeatherConverter.class})
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    @TypeConverters({MainConverter.class})
    private kg.geektech.weathergeektech.data.models.Main main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    @TypeConverters({WeatherConverter.class})
    private kg.geektech.weathergeektech.data.models.Wind wind;
    @SerializedName("clouds")
    @Expose
    @TypeConverters({CloudConverter.class})
    private kg.geektech.weathergeektech.data.models.Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    @TypeConverters({SysConverter.class})
    private Sys sys;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    public kg.geektech.weathergeektech.data.models.Coord getCoord() {
        return coord;
    }

    public void setCoord(kg.geektech.weathergeektech.data.models.Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public kg.geektech.weathergeektech.data.models.Main getMain() {
        return main;
    }

    public void setMain(kg.geektech.weathergeektech.data.models.Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public kg.geektech.weathergeektech.data.models.Wind getWind() {
        return wind;
    }

    public void setWind(kg.geektech.weathergeektech.data.models.Wind wind) {
        this.wind = wind;
    }

    public kg.geektech.weathergeektech.data.models.Clouds getClouds() {
        return clouds;
    }

    public void setClouds(kg.geektech.weathergeektech.data.models.Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
