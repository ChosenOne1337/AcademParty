package ru.ftc.android.shifttemple.features.parties.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

final class PartyInfoPresenter extends MvpPresenter<PartyInfoView> {
    private final PartiesInteractor interactor;

    PartyInfoPresenter(PartiesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        Party party = view.getParty();
        loadParty(party);
    }

    public void loadParty(Party party) {
        interactor.loadParty(party.getId(), new Carry<Party>() {

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

    public void deleteParty(Party party) {
        interactor.deleteParty(party.getId(), new Carry<Success>() {

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
}
