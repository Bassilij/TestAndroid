package test.testandroid;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import test.testandroid.Retrofit.Users;

/**
 * Created by Bassilij on 10.07.2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Name;
        ImageView imageView;
        TextView Age;
        TextView Similarity;
        TextView Datet;
        TextView Mess;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.message);
            Name = (TextView) itemView.findViewById(R.id.Name);
            imageView = (ImageView) itemView.findViewById(R.id.Image);
            Age = (TextView) itemView.findViewById(R.id.Age);
            Similarity = (TextView) itemView.findViewById(R.id.Similarity);
            Datet = (TextView) itemView.findViewById(R.id.Date);
            Mess = (TextView) itemView.findViewById(R.id.Mes);

            Mess.setBackgroundColor(Color.BLUE);
            Mess.setTextColor(Color.WHITE);
        }
    }

    List<Users> persons;
    RVAdapter(List<Users> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.Name.setText(persons.get(i).getName());
        personViewHolder.Age.setText(persons.get(i).getAge().toString()+" лет, ");
        //personViewHolder.Similarity.setText(persons.get(i).getSimilarity()+"%");
        personViewHolder.Datet.setText(persons.get(i).getLastSeen());
        //personViewHolder.Mess.setText(persons.get(i).getUnreadMessages()+"");
        //personViewHolder.imageView.setImageResource(persons.get(i).getAvatar());

        /////////////////////////////////////////////////////////////////////////////////////////

        try {
            String date = persons.get(i).getLastSeen();
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy KK:mm"); //KK:mm
            Date docDate= format.parse(date);

            SimpleDateFormat format2 = new SimpleDateFormat();
            format2.applyPattern("dd.MM.yyyy");

            SimpleDateFormat format3 = new SimpleDateFormat();
            format3.applyPattern("HH:mm");

            Date tekDate = new Date();

            if (format2.format(tekDate).equals(format2.format(docDate)))
                personViewHolder.Datet.setText("Сегодня, " + format3.format(docDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int Mes = persons.get(i).getUnreadMessages();
        if(Mes > 0)
            personViewHolder.Mess.setText(Mes+"");
        if(Mes > 999)
            personViewHolder.Mess.setText("999+");

        try {
            Glide
                    .with(personViewHolder.imageView.getContext())
                    .load(persons.get(i).getAvatar())
                    .override(75, 75)
                    .into(personViewHolder.imageView);
        } catch (Exception e){}


        //TextView Similarity = (TextView) card.findViewById(R.id.Similarity);
        int similarity = persons.get(i).getSimilarity();
        personViewHolder.Similarity.setText(similarity+"%");
        personViewHolder.Similarity.setTextColor(Color.GREEN);
        if(similarity <= 40)
            personViewHolder.Similarity.setTextColor(Color.RED);
        else if (similarity <= 70)
            personViewHolder.Similarity.setTextColor(Color.YELLOW);

        /////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


}