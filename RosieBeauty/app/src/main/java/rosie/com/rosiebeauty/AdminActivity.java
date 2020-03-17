package rosie.com.rosiebeauty;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rosie.com.rosiebeauty.Data.User;
import rosie.com.rosiebeauty.Data.UserRepository;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        RecyclerView historyTaskRecycler = (RecyclerView) findViewById(R.id.recycle_account);
        List<User> userList = new ArrayList<>(UserRepository.userList.values());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        CardViewAccountAdapter adapter = new CardViewAccountAdapter(userList, this);
        historyTaskRecycler.setLayoutManager(linearLayoutManager);
        historyTaskRecycler.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
