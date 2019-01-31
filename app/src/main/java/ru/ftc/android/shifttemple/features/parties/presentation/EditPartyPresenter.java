package ru.ftc.android.shifttemple.features.parties.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.network.Carry;

final class EditPartyPresenter extends MvpPresenter<EditPartyView> {

    private final PartiesInteractor interactor;

    EditPartyPresenter(PartiesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadParty(view.getPartyId());
    }

    private void loadParty(String partyId) {
        interactor.loadParty(partyId, new Carry<Party>() {

            @Override
            public void onSuccess(Party result) {
                view.showPartyFields(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void onPartyChanged(Party party) {
        interactor.editParty(party.getId(), party, new Carry<Party>() {

            @Override
            public void onSuccess(Party result) {
                view.showSuccessMessage();
                view.showPartyInfo(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }
}
