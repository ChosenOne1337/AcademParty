package ru.ftc.android.shifttemple.features.parties.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface PartiesRepository {

    void loadParties(Carry<List<Party>> carry);

    void loadParty(String id, Carry<Party> carry);

    void createParty(Party party, Carry<Party> carry);

    void deleteParty(String id, Carry<Success> carry);
}