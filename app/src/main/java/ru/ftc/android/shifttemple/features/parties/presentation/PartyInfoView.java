package ru.ftc.android.shifttemple.features.parties.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public interface PartyInfoView extends MvpView {
    String getPartyId();

    void showError(String message);

    void showSuccessMessage();

    void showPartyInfo(Party party);
}
