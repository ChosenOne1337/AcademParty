package ru.ftc.android.shifttemple.features.parties.domain.model;

/**
 * Created: samokryl
 * Date: 03.07.18
 * Time: 15:28
 */

public class Wrapper<T> {
    private String status;
    private String message;
    private T data;

    public Wrapper(T data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
