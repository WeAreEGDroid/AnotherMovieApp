package com.egdroid.presentation.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.egdroid.features.topratedmovies.usecase.TopRatedMovieUseCase;
import com.egdroid.models.mapper.MovieMapper;
import com.egdroid.models.uimodel.MovieUI;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created at Tito on 2019-10-12
 */
public class TopRatedMovieViewModel extends ViewModel {

    private MovieMapper mapper;
    private TopRatedMovieUseCase useCase;

    private CompositeDisposable disposable;

    public TopRatedMovieViewModel(TopRatedMovieUseCase useCase) {
        this.mapper = new MovieMapper();
        this.useCase = useCase;
        this.disposable = new CompositeDisposable();
    }

    public LiveData<Resource<List<MovieUI>>> getRefreshedTopRatedMoviesList(
            int pageNumber
    ) {

        MutableLiveData<Resource<List<MovieUI>>> listMutableLiveData = new MutableLiveData<>();

        disposable.add(useCase.getRefreshedTopRatedMoviesList(
                pageNumber
                ).map(
                movieEntities -> mapper.mapMovieEntityToMovieUi(movieEntities)

                ).doOnSubscribe(subscription ->
                        listMutableLiveData.postValue(Resource.loading())
                ).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(movieUIS -> listMutableLiveData.postValue(Resource.success(movieUIS)),
                                throwable -> listMutableLiveData.postValue(Resource.error(throwable))
                        )
        );

        return listMutableLiveData;

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
