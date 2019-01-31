package ru.ftc.android.shifttemple.features.parties.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Person;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

final class PartyInfoPresenter extends MvpPresenter<PartyInfoView> {
    private final PartiesInteractor interactor;

    PartyInfoPresenter(PartiesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadParty(view.getPartyId());
    }

    public void loadParty(String partyId) {
        interactor.loadParty(partyId, new Carry<Party>() {

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

    public void deleteParty(String partyId) {
        interactor.deleteParty(partyId, new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                view.showSuccessMessage();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void deletePerson(String partyId, Person person) {
        interactor.deletePerson(partyId, person, new Carry<Party>() {

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
