package ru.ftc.android.shifttemple.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ftc.android.shifttemple.exception.EmptyBodyException;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 23:55
 */


public final class DefaultCallback<T> implements Callback<T> {

    private final Carry<T> carry;

    public DefaultCallback(final Carry<T> carry) {
        this.carry = carry;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T data = response.body();
        if (data != null) {
            carry.onSuccess(data);
        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        carry.onFailure(t);
    }

}