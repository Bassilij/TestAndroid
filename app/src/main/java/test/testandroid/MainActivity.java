package test.testandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.testandroid.Retrofit.App;
import test.testandroid.Retrofit.Users;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter mHelloWorldPresenter;

    private TextView mHelloWorldTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloWorldTextView = ((TextView) findViewById(R.id.TextID));

        App.getApi().getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                TextView textView = (TextView)findViewById(R.id.TextID);
                textView.setText(response.body().get(0).getName());
                //////////////////////////////////////////////////////////////////////////////////////
                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.message);
                for (int i = 0; i < response.body().size(); i++) {
                    //CardView(i, response, mainLayout, response.body().get(i).getAvatar());
                    String name = response.body().get(i).getName();
                    String age = response.body().get(i).getAge().toString()+"лет, ";
                    int similarity = response.body().get(i).getSimilarity();
                    String link = response.body().get(i).getAvatar();
                    String date = response.body().get(i).getLastSeen();
                    int Mes = response.body().get(i).getUnreadMessages();


                    LayoutInflater inflater = getLayoutInflater();
                    View card = inflater.inflate(R.layout.card, null);

                    TextView Name = (TextView) card.findViewById(R.id.Name);
                    Name.setText(name);

                    TextView Age = (TextView) card.findViewById(R.id.Age);
                    Age.setText(age);

                    TextView Similarity = (TextView) card.findViewById(R.id.Similarity);
                    Similarity.setText(similarity+"%");
                    Similarity.setTextColor(Color.GREEN);
                    if(similarity <= 40)
                        Similarity.setTextColor(Color.RED);
                    else if (similarity <= 70)
                         Similarity.setTextColor(Color.YELLOW);

                    TextView Datet = (TextView) card.findViewById(R.id.Date);
                    Datet.setText(date);

                    try {
                        SimpleDateFormat format = new SimpleDateFormat();
                        format.applyPattern("dd.MM.yyyy KK:mm"); //KK:mm
                        Date docDate= format.parse(date);

                        SimpleDateFormat format2 = new SimpleDateFormat();
                        format2.applyPattern("dd.MM.yyyy");

                        SimpleDateFormat format3 = new SimpleDateFormat();
                        format3.applyPattern("HH:mm");

                        Date tekDate = new Date();

                        if (format2.format(tekDate).equals(format2.format(docDate)))
                            Datet.setText("Сегодня, " + format3.format(docDate));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    ImageView imageView = (ImageView) findViewById(R.id.Image);

                    try {
                        Glide
                                .with(MainActivity.this)
                                .load(link)
                                .override(75, 75)
                                .into(imageView);
                    } catch (Exception e){}


                    TextView Mess = (TextView) card.findViewById(R.id.Mes);
                    Mess.setBackgroundColor(Color.BLUE);
                    Mess.setTextColor(Color.WHITE);
                    if(Mes > 0)
                        Mess.setText(Mes+"");
                    if(Mes > 999)
                        Mess.setText("999+");

                    mainLayout.addView(card);
                }
                //////////////////////////////////////////////////////////////////////////////////////
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                TextView textView = (TextView)findViewById(R.id.TextID);
                textView.setText(t.toString());
                //Произошла ошибка
            }
        });

    }

    @Override
    public void showMessage(int message) {
        mHelloWorldTextView.setText(message);
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
///////////////////////////////////////////////////////////////////////////
        App.getApi().getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                TextView textView = (TextView)findViewById(R.id.TextID);
                textView.setText(response.body().get(0).getName());
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                TextView textView = (TextView)findViewById(R.id.TextID);
                textView.setText(t.toString());
                //Произошла ошибка
            }
        });
///////////////////////////////////////////////////////////////////////////

    }*/

    public void CardView(int i, Response response, LinearLayout mainLayout, String link){
        String name = "";// = response.body().get(i).getName();
        String age = "";// = response.body().get(i).getAge().toString()+"лет, ";
        String similarity = "";// = response.body().get(i).getSimilarity().toString()+"%";
        //String link;// = response.body().get(i).getAvatar();
        String date = "";// = response.body().get(i).getLastSeen();

        LayoutInflater inflater = getLayoutInflater();
        View card = inflater.inflate(R.layout.card, null);

        TextView Name = (TextView) card.findViewById(R.id.Name);
        Name.setText(card.getContext().toString());

        TextView Age = (TextView) card.findViewById(R.id.Age);
        Age.setText(age);

        TextView Similarity = (TextView) card.findViewById(R.id.Similarity);
        Similarity.setText(similarity);

        TextView Date = (TextView) card.findViewById(R.id.Date);
        Date.setText(date);

        ImageView imageView = (ImageView) findViewById(R.id.Image);

        try {
            Glide
                    .with(card.getContext())
                    .load(link)
                    .override(75, 75)
                    .into(imageView);
        } catch (Exception e){}

        mainLayout.addView(card);
    }
}
