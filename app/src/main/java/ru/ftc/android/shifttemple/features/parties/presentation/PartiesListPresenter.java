package ru.ftc.android.shifttemple.features.parties.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.SortOrder;
import ru.ftc.android.shifttemple.features.parties.domain.SortParameter;
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
        loadParties(view.sortListBy(), view.listSortOrder());
    }

    public void loadParties() {
//        view.showProgress();
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

    public void loadParties(SortParameter sortBy, SortOrder sortOrder) {
//        view.showProgress();
        interactor.loadParties(sortBy, sortOrder, new Carry<List<Party>>() {

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


    public void onPartySelected(Party party) {
        view.showProgress();
        // get actual party information
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
}