package ru.ftc.android.shifttemple.features.parties.presentation;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public interface PartyCreationView extends MvpView {
    void showCreatedParty(Party party);

    void showError(String message);

    void showSuccessMessage();
}
