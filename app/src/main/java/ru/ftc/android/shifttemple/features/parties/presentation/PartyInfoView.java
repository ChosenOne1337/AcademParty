package ru.ftc.android.shifttemple.features.parties.presentation;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public interface PartyInfoView extends MvpView {
    Party getParty();

    void showError(String message);

    void showSuccessMessage();

    void showPartyInfo(Party party);
}
