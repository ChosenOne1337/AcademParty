package ru.ftc.android.shifttemple.features.parties.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:22
 */

interface PartyListView extends MvpView {
    void showProgress();

    void hideProgress();

    void showPartyList(List<Party> list);

    void showError(String message);

}
