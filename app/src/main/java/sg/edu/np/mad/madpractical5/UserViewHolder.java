package sg.edu.np.mad.madpractical5;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView profile;
    ImageView largeimg;
    TextView name;
    TextView description;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        profile = itemView.findViewById(R.id.profile);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        largeimg = itemView.findViewById(R.id.largeimg);
    }
}