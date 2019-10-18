package com.gleysonabreu.cardapio.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.gleysonabreu.cardapio.R
import com.gleysonabreu.cardapio.adapter.CardapioAdapter
import com.gleysonabreu.cardapio.adapter.EmpresaAdapter
import com.gleysonabreu.cardapio.helper.SettingsFirebase
import com.gleysonabreu.cardapio.model.Cardapio
import com.gleysonabreu.cardapio.model.Empresa
import com.google.firebase.database.*
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empresas_items.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class MainActivity : AppCompatActivity() {

    private var listaCardapio: ArrayList<Cardapio> = ArrayList<Cardapio>();
    private lateinit var  linearLayoutManager: LinearLayoutManager;
    private lateinit var  childEventListener: ChildEventListener;
    private lateinit var  firebaseRef: DatabaseReference;
    private lateinit var  cardapioRef: DatabaseReference;
    private var listKey: ArrayList<String> = ArrayList();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this);
        // Toolbar settings
        setSupportActionBar(toolbar);

        // FirebaseSettings
        firebaseRef = SettingsFirebase.getFirebase();
        cardapioRef = firebaseRef.child("cardapio");

        // Recycler Settings
        recyclerCardapio.adapter = CardapioAdapter(listaCardapio, this);
        recyclerCardapio.layoutManager = linearLayoutManager;

        search_view.setOnSearchViewListener(object: MaterialSearchView.SearchViewListener{
            override fun onSearchViewShown() {

            }

            override fun onSearchViewClosed() {
                returnCardapio();
            }

        })

        /// Settings Search View
        search_view.setHint("Pesquisar produtos no cardápio");
        search_view.setOnQueryTextListener(object: MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if( newText != null && !newText.isEmpty() ){

                    searchMenu(newText)

                }

                return true;

            }

        })

    }

    private fun returnCardapio(){

        recyclerCardapio.adapter = CardapioAdapter(listaCardapio, this@MainActivity);
        recyclerCardapio.adapter?.notifyDataSetChanged();

    }

    private fun searchMenu(text: String){
            var searchMenu: ArrayList<Cardapio> = ArrayList<Cardapio>();

        for(cardapio in listaCardapio){
            var nameitem = cardapio.nameitem.toLowerCase();
            if( nameitem.contains( text.toLowerCase() ) ){
                searchMenu.add(cardapio);
            }
        }

        recyclerCardapio.adapter = CardapioAdapter(searchMenu, this@MainActivity);
        recyclerCardapio.adapter?.notifyDataSetChanged();

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater;
        inflater.inflate(R.menu.menu_app, menu);
        val item: MenuItem? = menu?.findItem(R.id.search);
        search_view.setMenuItem(item);


        return super.onCreateOptionsMenu(menu)
    }

    private fun cardapio(){

        childEventListener = cardapioRef.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("Cancelled", p0.message);
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                Log.e("Moved", p0.toString());
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                var cardapioChanged = p0.getValue(Cardapio::class.java)
                var index = listKey.indexOf(p0.key);

                if(cardapioChanged != null){
                    listaCardapio.set(index, cardapioChanged);
                    recyclerCardapio.adapter?.notifyDataSetChanged();
                }

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                var cardapio = p0.getValue(Cardapio::class.java);
                if (cardapio != null) {
                    listaCardapio.add(cardapio);
                    p0.key?.let { listKey.add(it) }
                };

                recyclerCardapio.adapter?.notifyDataSetChanged();

            }

            override fun onChildRemoved(p0: DataSnapshot) {

                var index = listKey.indexOf(p0.key);
                listaCardapio.removeAt(index);
                listKey.removeAt(index);

                recyclerCardapio.adapter?.notifyDataSetChanged();

            }

        })

    }

    override fun onStart() {
        super.onStart()

        cardapio();

    }

    override fun onDestroy() {
        super.onDestroy()
        cardapioRef.removeEventListener(childEventListener);
    }
}
