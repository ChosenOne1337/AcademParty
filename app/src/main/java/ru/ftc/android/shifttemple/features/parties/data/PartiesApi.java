package ru.ftc.android.shifttemple.features.parties.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface PartiesApi {

    @GET("parties")
    Call<List<Party>> getPartyList();

    @GET("parties/{id}")
    Call<Party> getParty(@Path("id") String id);

    @POST("parties")
    Call<Party> createParty(@Body Party party);

    @DELETE("parties/{id}")
    Call<Success> deleteParty(@Path("id") String id);

}
