package ru.ftc.android.shifttemple.features.parties.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface PartiesApi {

    @GET("parties")
    Call<List<Party>> getPartyList();

    @GET("parties")
    Call<List<Party>> getPartyList(@Query("param") List<String> params);

    @GET("parties/{id}")
    Call<Party> getParty(@Path("id") String id);

    @POST("parties")
    Call<Party> createParty(@Body Party party);

    @DELETE("parties/{id}")
    Call<Success> deleteParty(@Path("id") String id);

    @PATCH("parties/addperson/{partyId}")
    Call<Party> addPerson(@Path("partyId") String partyId, @Body Person person);

    @PATCH("parties/deleteperson/{partyId}")
    Call<Party> deletePerson(@Path("partyId") String partyId, @Body Person person);

    @PATCH("parties/{id}")
    Call<Party> editParty(@Path("id") String id, @Body Party party);

}
