package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context context;
    List<User> data;

    public UserAdapter(List<User> input, Context context){
        this.context = context;
        data = input;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_activity_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = data.get(position);
        holder.name.setText(user.name);
        holder.description.setText(user.description);
        if (user.name.charAt(user.name.length() - 1) == '7') {
            holder.largeimg.setVisibility(View.VISIBLE);
        }
        else{
            holder.largeimg.setVisibility(View.GONE);
        }

        holder.profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Profile");
                builder.setMessage("MADness");
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int val = new Random().nextInt(1000000);
                        String rannum = Integer.toString(val);
                        Intent mainactivity = new Intent(context, MainActivity.class);
                        mainactivity.putExtra("randomnum", user.getName());
                        mainactivity.putExtra("randdesc", user.getDescription());
                        mainactivity.putExtra("randfollow", user.getFollowed());
                        context.startActivity(mainactivity);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

