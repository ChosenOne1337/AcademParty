package ru.ftc.android.shifttemple.features.parties.data;

import java.util.List;

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
    public void addPerson(String id, Person person, Carry<Party> carry) {
        partiesApi.addPerson(id, person).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void editParty(String id, Party party, Carry<Party> carry) {
        partiesApi.editParty(id, party).enqueue(new DefaultCallback(carry));
    }
}