package ru.ftc.android.shifttemple.features.parties.data;

import java.util.Arrays;
import java.util.List;

import ru.ftc.android.shifttemple.features.parties.domain.SortOrder;
import ru.ftc.android.shifttemple.features.parties.domain.SortParameter;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public final class PartiesDataSourceImpl implements PartiesDataSource {

    private final PartiesApi partiesApi;

    public PartiesDataSourceImpl(PartiesApi partiesApi) {
        this.partiesApi = partiesApi;
    }

    @Override
    public void getParties(final Carry<List<Party>> carry) {
        partiesApi.getPartyList().enqueue(new DefaultCallback(carry));
    }

    public void getParties(SortParameter sortBy, SortOrder sortOrder, Carry<List<Party>> carry) {
        List<String> params = Arrays.asList(sortBy.toString(), sortOrder.toString());
        partiesApi.getPartyList(params).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getParty(String id, Carry<Party> carry) {
        partiesApi.getParty(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createParty(Party party, Carry<Party> carry) {
        partiesApi.createParty(party).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteParty(String id, Carry<Success> carry) {
        partiesApi.deleteParty(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void addPerson(String partyId, Person person, Carry<Party> carry) {
        partiesApi.addPerson(partyId, person).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deletePerson(String partyId, Person person, Carry<Party> carry) {
        partiesApi.deletePerson(partyId, person).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void editParty(String id, Party party, Carry<Party> carry) {
        partiesApi.editParty(id, party).enqueue(new DefaultCallback(carry));
    }
}