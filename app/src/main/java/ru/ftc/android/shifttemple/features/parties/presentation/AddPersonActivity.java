package ru.ftc.android.shifttemple.features.parties.presentation;

import android.os.Bundle;

import ru.ftc.android.shifttemple.R;

import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.parties.domain.model.Party;

public class AddPersonActivity extends BaseActivity implements AddPersonView {
    private AddPersonPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_person_activity);

        initView();
    }

    private void initView() {

    }

    @Override
    protected MvpPresenter<AddPersonView> getPresenter() {
        presenter = PresenterFactory.createAddPersonPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public String getPartyId() {
        return null;
    }

    @Override
    public void showPartyInfo(Party party) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showSuccessMessage() {

    }
}
