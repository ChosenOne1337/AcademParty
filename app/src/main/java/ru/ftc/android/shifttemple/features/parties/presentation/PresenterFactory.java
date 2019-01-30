package ru.ftc.android.shifttemple.features.parties.presentation;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.parties.data.PartiesApi;
import ru.ftc.android.shifttemple.features.parties.data.PartiesDataSource;
import ru.ftc.android.shifttemple.features.parties.data.PartiesDataSourceImpl;
import ru.ftc.android.shifttemple.features.parties.data.PartiesRepository;
import ru.ftc.android.shifttemple.features.parties.data.PartiesRepositoryImpl;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractor;
import ru.ftc.android.shifttemple.features.parties.domain.PartiesInteractorImpl;
import ru.ftc.android.shifttemple.features.parties.presentation.PartiesListPresenter;
import ru.ftc.android.shifttemple.features.parties.presentation.PartyInfoPresenter;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 1:03
 */

final class PresenterFactory {

    static PartiesListPresenter createPartiesListPresenter(Context context) {
        final PartiesApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PartiesApi.class);

        final PartiesDataSource dataSource = new PartiesDataSourceImpl(api);
        final PartiesRepository repository = new PartiesRepositoryImpl(dataSource);
        final PartiesInteractor interactor = new PartiesInteractorImpl(repository);

        return new PartiesListPresenter(interactor);
    }

    static PartyInfoPresenter createPartyInfoPresenter(Context context) {
        final PartiesApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PartiesApi.class);

        final PartiesDataSource dataSource = new PartiesDataSourceImpl(api);
        final PartiesRepository repository = new PartiesRepositoryImpl(dataSource);
        final PartiesInteractor interactor = new PartiesInteractorImpl(repository);

        return new PartyInfoPresenter(interactor);
    }

    static EditPartyPresenter createEditPartyPresenter(Context context) {
        final PartiesApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PartiesApi.class);

        final PartiesDataSource dataSource = new PartiesDataSourceImpl(api);
        final PartiesRepository repository = new PartiesRepositoryImpl(dataSource);
        final PartiesInteractor interactor = new PartiesInteractorImpl(repository);

        return new EditPartyPresenter(interactor);
    }

    static PartyCreationPresenter createPartyCreationPresenter(Context context) {
        final PartiesApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PartiesApi.class);

        final PartiesDataSource dataSource = new PartiesDataSourceImpl(api);
        final PartiesRepository repository = new PartiesRepositoryImpl(dataSource);
        final PartiesInteractor interactor = new PartiesInteractorImpl(repository);

        return new PartyCreationPresenter(interactor);
    }

}