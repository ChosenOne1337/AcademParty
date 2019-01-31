package ru.ftc.android.shifttemple.network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ftc.android.shifttemple.exception.FailedRequestException;
import ru.ftc.android.shifttemple.features.parties.domain.model.ServerError;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 23:55
 */


public final class DefaultCallback<T> implements Callback<T> {

    private static Gson gson = new Gson();
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
            String errorText;
            try {
                String responseBody = response.raw().body().string();
                errorText = parseError(responseBody);
            } catch (Throwable e) {
                errorText = response.message();
            }
            carry.onFailure(new FailedRequestException(errorText));

        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        carry.onFailure(t);
    }

    private String parseError(String body) {
        return gson.fromJson(body, ServerError.class).getMessage();
    }
}