package ru.ftc.android.shifttemple.features.parties.presentation;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;
import ru.ftc.android.shifttemple.network.Carry;

final class AddPersonPresenter extends MvpPresenter<AddPersonView> {
    private final PartiesInteractor interactor;

    AddPersonPresenter(PartiesInteractor interactor) {
        this.interactor = interactor;
    }

    public void addPerson(String partyId, Person person) {
        interactor.addPerson(partyId, person, new Carry<Party>() {

            @Override
            public void onSuccess(Party result) {
                view.showPartyInfo(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }
}
