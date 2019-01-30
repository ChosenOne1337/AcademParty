package ru.ftc.android.shifttemple.features.parties.presentation;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public interface EditPartyView extends MvpView {
    String getPartyId();

    void showPartyFields(Party party);

    void showError(String message);

    void showSuccessMessage();
}
