package gdgstudyjam.assignment1;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdvancedCustomArrayAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] rappers;
    private final Integer[] imageIds;
    private final String[] descb;
    public DataSnapshot snapshot1;
    public Firebase myFirebaseRef;

    public AdvancedCustomArrayAdapter(
            Activity context, String[] rappers, Integer[] imageIds,String [] descb) {
        super(context, R.layout.layout2, rappers);
        this.context = context;
        this.rappers = rappers;
        this.imageIds = imageIds;
        this.descb=descb;
        Firebase.setAndroidContext(getContext());
        myFirebaseRef=new Firebase("https://rappers.firebaseio.com/");
    }

    static class ViewContainer {
        public ImageView imageView;
        public TextView txtTitle;
        public TextView txtDescription;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewContainer viewContainer;
        View rowView = view;
        final int pos=position;
        //---print the index of the row to examine---
        Log.d("CustomArrayAdapter",String.valueOf(position));

        //---if the row is displayed for the first time---
        if (rowView == null) {

            Log.d("CustomArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.layout2, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();

            //---get the references to all the views in the row---
            viewContainer.txtTitle = (TextView)
                    rowView.findViewById(R.id.txtRapperName);
            viewContainer.txtDescription = (TextView)
                    rowView.findViewById(R.id.txtDescription);
            viewContainer.imageView = (ImageView) rowView.findViewById(R.id.icon);

            //---assign the view container to the rowView---
            rowView.setTag(viewContainer);
        } else {

            //---view was previously created; can recycle---            
            Log.d("CustomArrayAdapter", "Recycling");
            //---retrieve the previously assigned tag to get
            // a reference to all the views; bypass the findViewByID() process,
            // which is computationally expensive---
            viewContainer = (ViewContainer) rowView.getTag();
        }



        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                viewContainer.txtTitle.setText(String.valueOf(dataSnapshot.child("raplist").child(String.valueOf(pos)).child("Name").getValue()));
                Picasso.with(getContext()).load(String.valueOf(dataSnapshot.child("raplist").child(String.valueOf(pos)).child("imageURL").getValue())).into(viewContainer.imageView);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        return rowView;
    }

}


