package com.example.android.achievement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import adapter.AchievementAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import room.AchievementEntity;
import room.AchievementViewModel;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_ACHIEVEMENT_REQ_CODE = 1;
    public static final int EDIT_ACHIEVEMENT_REQ_CODE = 2;

    private AchievementViewModel achievementViewModel;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmptyRecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setEmptyView(findViewById(R.id.empty_view));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        final AchievementAdapter adapter = new AchievementAdapter();
        recyclerView.setAdapter(adapter);

        achievementViewModel = ViewModelProviders.of(this).get(AchievementViewModel.class);
        achievementViewModel.getAllAchievements().observe(this, new Observer<List<AchievementEntity>>() {
            @Override
            public void onChanged(List<AchievementEntity> achievements) {
                //update RecyclerView
                adapter.setAchievementList(achievements);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.delete_dialog_msg);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        achievementViewModel.delete(adapter.getAchievementAt(viewHolder.getAdapterPosition()));

                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        }).attachToRecyclerView(recyclerView);

        fab = findViewById(R.id.add_icon);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivityForResult(intent, ADD_ACHIEVEMENT_REQ_CODE);
            }
        });

        adapter.setOnItemClickListener(new AchievementAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AchievementEntity achievement) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                intent.putExtra(EditorActivity.EXTRA_ID, achievement.getId());
                intent.putExtra(EditorActivity.EXTRA_TITLE, achievement.getTitle());
                intent.putExtra(EditorActivity.EXTRA_DETAILS, achievement.getDetails());
                intent.putExtra(EditorActivity.EXTRA_DATE, achievement.getDate());
                intent.putExtra(EditorActivity.EXTRA_TYPE, achievement.getType());
                startActivityForResult(intent, EDIT_ACHIEVEMENT_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ACHIEVEMENT_REQ_CODE
                && resultCode == RESULT_OK) {
            String title = data.getStringExtra(EditorActivity.EXTRA_TITLE);
            String details = data.getStringExtra(EditorActivity.EXTRA_DETAILS);
            String date = data.getStringExtra(EditorActivity.EXTRA_DATE);
            String type = data.getStringExtra(EditorActivity.EXTRA_TYPE);
            AchievementEntity achievement = new AchievementEntity(title, details, type, date);
            achievementViewModel.insert(achievement);
        } else if (requestCode == EDIT_ACHIEVEMENT_REQ_CODE
                && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditorActivity.EXTRA_ID, -1);
            if (id == -1) {
                return;
            }
            String title = data.getStringExtra(EditorActivity.EXTRA_TITLE);
            String details = data.getStringExtra(EditorActivity.EXTRA_DETAILS);
            String date = data.getStringExtra(EditorActivity.EXTRA_DATE);
            String type = data.getStringExtra(EditorActivity.EXTRA_TYPE);
            AchievementEntity achievement = new AchievementEntity(title, details, type, date);
            achievement.setId(id);
            achievementViewModel.update(achievement);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll_icon:
                showDeleteAllConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteAllConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteall_dialog_msg);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAll();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void deleteAll() {
        achievementViewModel.deleteAllAchievements();
    }
}
