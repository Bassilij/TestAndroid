package test.testandroid.Retrofit;

/**
 * Created by Bassilij on 05.07.2017.
 */

        import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static APIService ret;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://a11d.firebaseio.com/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        ret = retrofit.create(APIService.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static APIService getApi() {
        return ret;
    }
}