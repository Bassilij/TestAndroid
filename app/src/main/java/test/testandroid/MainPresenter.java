package test.testandroid;

import android.support.v7.widget.RecyclerView;

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
    private RecyclerView rv;

    public MainPresenter() {
        getViewState().showMessage(R.string.hello_world);

        ////////////////////////
        /*rv=(RecyclerView)findViewById(R.id.message);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();*/
        /////////////////////
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
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                //Произошла ошибка
            }
        });
//        persons = new ArrayList<>();
//        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
//        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.lavery));
//        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.lillie));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }
}
