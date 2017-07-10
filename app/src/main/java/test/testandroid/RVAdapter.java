package test.testandroid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        personViewHolder.Age.setText(persons.get(i).getAge().toString()+"лет, ");
        personViewHolder.Similarity.setText(persons.get(i).getSimilarity()+"");
        personViewHolder.Datet.setText(persons.get(i).getLastSeen());
        personViewHolder.Mess.setText(persons.get(i).getUnreadMessages()+"");
        //personViewHolder.imageView.setImageResource(persons.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


}