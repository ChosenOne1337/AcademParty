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
 * Time: 22:57
 */

public interface PartiesDataSource {

    void getParties(Carry<List<Party>> carry);

    void getParties(SortParameter sortBy, SortOrder sortOrder, Carry<List<Party>> carry);

    void getParty(String id, Carry<Party> carry);

    void createParty(Party party, Carry<Party> carry);

    void deleteParty(String id, Carry<Success> carry);

    void addPerson(String partyId, Person person, Carry<Party> carry);

    void deletePerson(String partyId, Person person, Carry<Party> carry);

    void editParty(String id, Party party, Carry<Party> carry);
}
