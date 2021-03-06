package ru.ftc.android.shifttemple.features.parties.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.parties.domain.SortOrder;
import ru.ftc.android.shifttemple.features.parties.domain.SortParameter;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class PartiesRepositoryImpl implements PartiesRepository {

    private final PartiesDataSource dataSource;

    public PartiesRepositoryImpl(PartiesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadParties(Carry<List<Party>> carry) {
        dataSource.getParties(carry);
    }

    @Override
    public void loadParties(SortParameter sortBy, SortOrder sortOrder, Carry<List<Party>> carry) {
        dataSource.getParties(sortBy, sortOrder, carry);
    }

    @Override
    public void loadParty(String id, Carry<Party> carry) {
        dataSource.getParty(id, carry);
    }

    @Override
    public void createParty(Party party, Carry<Party> carry) {
        dataSource.createParty(party, carry);
    }

    @Override
    public void deleteParty(String id, Carry<Success> carry) {
        dataSource.deleteParty(id, carry);
    }

    @Override
    public void addPerson(String partyId, Person person, Carry<Party> carry) {
        dataSource.addPerson(partyId, person, carry);
    }

    @Override
    public void deletePerson(String partyId, Person person, Carry<Party> carry) {
        dataSource.deletePerson(partyId, person, carry);
    }

    @Override
    public void editParty(String id, Party party, Carry<Party> carry) {
        dataSource.editParty(id, party, carry);
    }
}