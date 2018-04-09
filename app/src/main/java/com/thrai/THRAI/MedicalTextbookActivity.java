package com.thrai.THRAI;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.thrai.THRAI.Adapters.MedicalBooksRecyclerViewAdapter;
import com.thrai.THRAI.Connection.MedicalTextbookReq;
import com.thrai.THRAI.Connection.MedicalTextbookRes;
import com.thrai.THRAI.Connection.Response;
import com.thrai.THRAI.Connection.ServerHandlerAsyncTask;
import com.thrai.THRAI.Connection.ServerInterface;
import com.thrai.THRAI.Interfaces.OnBookListFragmentInteractionListener;
import com.thrai.THRAI.ModelClasses.MedicalBook;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MedicalTextbookActivity extends AppCompatActivity implements OnBookListFragmentInteractionListener {

    private RecyclerView recyclerView;
    private MedicalBooksRecyclerViewAdapter adapter;
    private List<MedicalBook> books;

    private AlertDialog loadingDialog = null;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_textbook);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        books = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicalBooksRecyclerViewAdapter(books, this);
        recyclerView.setAdapter(adapter);

        serverHandler();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(MedicalBook item) {
        Intent intent = new Intent(this,BookDetailTabbedActivity.class);
        intent.putExtra("data",item);
        startActivity(intent);
    }


    private void serverHandler(){
        showLoadingView();
        new ServerHandlerAsyncTask(this, new MedicalTextbookReq(), new MedicalTextbookRes(), new ServerInterface() {
            @Override
            public void onResponse(Response res) {
                MedicalTextbookRes rsp = (MedicalTextbookRes) res;
                books = rsp.getBooks();
                updateList();
                hideLoadingView();
            }
        }).execute();
    }


    public void updateList(){
        adapter = new MedicalBooksRecyclerViewAdapter(books, this);
        recyclerView.setAdapter(adapter);
    }


    private void showLoadingView(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View vi = inflater.inflate(R.layout.loading_view_layout,null);

        builder.setView(vi);
        loadingDialog = builder.create();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loadingDialog.show();
    }

    private void hideLoadingView(){
        if(loadingDialog != null){
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }
}
