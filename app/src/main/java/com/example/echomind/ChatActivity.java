package com.example.echomind;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerMessages;
    private EditText editMessage;
    private MessageAdapter adapter;
    private final List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        drawerLayout = findViewById(R.id.drawerLayout);
        recyclerMessages = findViewById(R.id.recyclerMessages);
        editMessage = findViewById(R.id.editMessage);

        adapter = new MessageAdapter(messages);
        recyclerMessages.setLayoutManager(new LinearLayoutManager(this));
        recyclerMessages.setAdapter(adapter);

        // رسالة ترحيب أولى
        addMessage("مرحباً بك في Synapse AI، اسأل أي سؤال وسأساعدك.", Message.TYPE_AI);

        findViewById(R.id.btnMenu).setOnClickListener(v ->
                drawerLayout.openDrawer(GravityCompat.START));

        findViewById(R.id.btnSend).setOnClickListener(v -> sendMessage());

        findViewById(R.id.btnNewChat).setOnClickListener(v -> {
            messages.clear();
            adapter.notifyDataSetChanged();
            addMessage("مرحباً بك في Synapse AI، اسأل أي سؤال وسأساعدك.", Message.TYPE_AI);
        });

        // عناصر القائمة الجانبية
        LinearLayout navNewChat = findViewById(R.id.navNewChat);
        LinearLayout navFavorites = findViewById(R.id.navFavorites);
        LinearLayout navSettings = findViewById(R.id.navSettings);
        LinearLayout navAbout = findViewById(R.id.navAbout);

        if (navNewChat != null) navNewChat.setOnClickListener(v -> {
            messages.clear();
            adapter.notifyDataSetChanged();
            addMessage("مرحباً بك في Synapse AI، اسأل أي سؤال وسأساعدك.", Message.TYPE_AI);
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        if (navFavorites != null) navFavorites.setOnClickListener(v -> {
            startActivity(new Intent(this, FavoritesActivity.class));
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        if (navSettings != null) navSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        if (navAbout != null) navAbout.setOnClickListener(v -> {
            startActivity(new Intent(this, AboutActivity.class));
            drawerLayout.closeDrawer(GravityCompat.START);
        });
    }

    private void sendMessage() {
        String text = editMessage.getText().toString().trim();
        if (TextUtils.isEmpty(text)) return;
        addMessage(text, Message.TYPE_USER);
        editMessage.setText("");
        // رد وهمي مؤقت (هيتبدل بالذكاء الاصطناعي الحقيقي لاحقاً)
        addMessage("هذا رد تجريبي. سيتم ربط الذكاء الاصطناعي لاحقاً.", Message.TYPE_AI);
    }

    private void addMessage(String text, int type) {
        messages.add(new Message(text, type));
        adapter.notifyItemInserted(messages.size() - 1);
        recyclerMessages.scrollToPosition(messages.size() - 1);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
