package test.testandroid.Retrofit;

/**
 * Created by Bassilij on 05.07.2017.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/users.json")
    Call<List<Users>> getUsers();
}