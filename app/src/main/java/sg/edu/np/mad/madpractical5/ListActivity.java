package sg.edu.np.mad.madpractical5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        List<User> userList = new ArrayList<User>();

        userList = dbHandler.getUsers();

        if (userList.isEmpty()) {
            Random rand = new Random();
            for (int i = 0; i < 20; i++) {
                int val = rand.nextInt(1000000);
                String rannum = Integer.toString(val);
                boolean following = rand.nextBoolean();
                userList.add(new User("MAD " + rannum, "Description " + rannum, i, following));
            }

            for (User user : userList) {
                dbHandler.addUser(user);
            }
        }
        //To resubmit

        userList = dbHandler.getUsers();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        UserAdapter mAdapter = new UserAdapter(userList,this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}