package ru.ftc.android.shifttemple.features.parties.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.parties.data.PartiesRepository;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class PartiesInteractorImpl implements PartiesInteractor {

    private final PartiesRepository repository;

    public PartiesInteractorImpl(PartiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadParties(Carry<List<Party>> carry) {
        repository.loadParties(carry);
    }

    @Override
    public void loadParty(String id, Carry<Party> carry) {
        repository.loadParty(id, carry);
    }

    @Override
    public void createParty(Party party, Carry<Party> carry) {
        repository.createParty(party, carry);
    }

    @Override
    public void deleteParty(String id, Carry<Success> carry) {
        repository.deleteParty(id, carry);
    }

    @Override
    public void addPerson(String id, Person person, Carry<Party> carry) {
        repository.addPerson(id, person, carry);
    }

    @Override
    public void editParty(String id, Party party, Carry<Party> carry) {
        repository.editParty(id, party, carry);
    }
}