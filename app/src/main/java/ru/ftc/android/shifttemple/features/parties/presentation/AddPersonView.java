package ru.ftc.android.shifttemple.features.parties.presentation;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public interface AddPersonView extends MvpView {
    String getPartyId();

    void showPartyInfo(Party party);

    void showError(String message);

    void showSuccessMessage();
}
