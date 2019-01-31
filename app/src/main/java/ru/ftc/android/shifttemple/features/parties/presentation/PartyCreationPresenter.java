package ru.ftc.android.shifttemple.features.parties.presentation;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.network.Carry;

final class PartyCreationPresenter extends MvpPresenter<PartyCreationView> {
    private final PartiesInteractor interactor;

    PartyCreationPresenter(PartiesInteractor interactor) {
        this.interactor = interactor;
    }

    public void onPartyCreated(Party party) {
        interactor.createParty(party, new Carry<Party>() {

            @Override
            public void onSuccess(Party result) {
                view.showSuccessMessage();
                view.showCreatedParty(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }
}
