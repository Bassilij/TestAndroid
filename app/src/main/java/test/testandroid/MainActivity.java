package test.testandroid;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
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
    //22//
    private List<Users> persons;
    private RecyclerView rv;
    //22//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///////////////////////RV///////////////////////////
        rv=(RecyclerView)findViewById(R.id.message);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);


        mHelloWorldTextView = ((TextView) findViewById(R.id.TextID));

        initializeData();
        //initializeAdapter();
        ////////////////////////////////////////////////////


        /*
        mHelloWorldTextView = ((TextView) findViewById(R.id.TextID));

        App.getApi().getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                TextView textView = (TextView)findViewById(R.id.TextID);
                textView.setText(response.body().get(0).getName());
                //////////////////////////////////////////////////////////////////////////////////////
                RecyclerView mainLayout = (RecyclerView) findViewById(R.id.message);
                //LinearLayoutManager llm = new LinearLayoutManager(context);
                //mainLayout.setLayoutManager(llm);



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
*/
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

    private void initializeData(){
        App.getApi().getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                //Данные успешно пришли, но надо проверить response.body() на null
                persons = new ArrayList<>();
                for (int i = 0; i < response.body().size(); i++) {
                    persons.add(response.body().get(i));
                    System.out.println("Test!!! response " + response.body().get(i).toString());
                    System.out.println("Test!!! response " + persons.get(i).toString());
                }
                System.out.println("Test!!! response " + response.body().toString());
                initializeAdapter();///////!
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
        System.out.println("Test!!! response " + persons.get(0).toString());
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }
}
