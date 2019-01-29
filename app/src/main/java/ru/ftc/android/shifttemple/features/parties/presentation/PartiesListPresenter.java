package ru.ftc.android.shifttemple.features.parties.presentation;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;
import ru.ftc.android.shifttemple.features.parties.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:43
 */

final class PartiesListPresenter extends MvpPresenter<PartyListView> {

    private final PartiesInteractor interactor;

    PartiesListPresenter(PartiesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadParties();
    }

    private void loadParties() {
        view.showProgress();
        interactor.loadParties(new Carry<List<Party>>() {

            @Override
            public void onSuccess(List<Party> result) {
                view.showPartyList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    void onBookSelected(Party party) {
        view.showProgress();
        interactor.loadParty(party.getId(), new Carry<Party>() {

            @Override
            public void onSuccess(Party result) {
                view.hideProgress();
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void onAddPartyClicked() {

    }
}