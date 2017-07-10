package test.testandroid;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.testandroid.Retrofit.App;
import test.testandroid.Retrofit.Users;

/**
 * Created by Bassilij on 05.07.2017.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private List<Users> persons;

    public MainPresenter() {
        initializeData();
    }

    private void initializeData(){
        App.getApi().getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                persons = new ArrayList<>();
                for (int i = 0; i < response.body().size(); i++) {
                    persons.add(response.body().get(i));
                }
                //initializeAdapter();
                getViewState().showMessage(R.string.hello_world, persons);
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                //Произошла ошибка
            }
        });
    }
}
