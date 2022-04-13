package kg.geektech.weathergeektech.common;

import androidx.annotation.NonNull;

public class RequestState<T> {
    @NonNull
    public final Status status;
    public final T data;
    public final String msg;

    public RequestState(@NonNull Status status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <T> RequestState<T> success(@NonNull T data) {
        return new RequestState<>(Status.SUCCESS, data, null);
    }

    public static <T> RequestState<T> error(String msg, @NonNull T data) {
        return new RequestState<>(Status.ERROR, data, msg);
    }

    public static <T> RequestState<T> loading() {
        return new RequestState<>(Status.LOADING, null, null);
    }
}
