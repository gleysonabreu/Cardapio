package com.gleysonabreu.cardapio.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.gleysonabreu.cardapio.adapter.EmpresaAdapter
import com.gleysonabreu.cardapio.model.Empresa
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empresas_items.*
import kotlinx.android.synthetic.main.toolbar.*









class MainActivity : AppCompatActivity() {

    private var lista = ArrayList<Empresa>();
    private lateinit var  linearLayoutManager: LinearLayoutManager;
    private val SCROLL_DIRECTION_UP = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this);
        val itemOnClick: (View, Int, Int) -> Unit = {
          view, position, type -> run {
            val intent: Intent = Intent(this, CardapioActivity::class.java);
            intent.putExtra("dadosEmpresa", lista[position]);
            startActivity(intent);
            }
        };

        //Lista Teste empresas
        val empresa = Empresa();
        empresa.nome = "China in Box";
        empresa.tempo = "35-40min";
        empresa.tipo = "Lanche";
        empresa.foto = "n";
        lista.add(empresa);
        lista.add(empresa);
        lista.add(empresa);
        lista.add(empresa);
        lista.add(empresa);
        lista.add(empresa);

        val empresa2 = Empresa();
        empresa2.nome = "China in Box2";
        empresa2.tempo = "35-40min";
        empresa2.tipo = "Lanche";
        empresa2.foto = "n";
        lista.add(empresa2);
        lista.add(empresa2);
        lista.add(empresa2);
        lista.add(empresa2);
        lista.add(empresa2);
        lista.add(empresa2);

        val empresa3 = Empresa();
        empresa3.nome = "China in Box3";
        empresa3.tempo = "35-40min";
        empresa3.tipo = "Lanche";
        empresa3.foto = "n";
        lista.add(empresa3);
        lista.add(empresa3);
        lista.add(empresa3);
        lista.add(empresa3);
        lista.add(empresa3);
        lista.add(empresa3);


        // Settings Toolbar
        setSupportActionBar(toolbar);

        //RecyclerView
        val emp_recy = recyclerListaEmpresas;
        emp_recy.adapter = EmpresaAdapter(lista, this, itemOnClick);
        emp_recy.layoutManager = linearLayoutManager;

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater;
        inflater.inflate(com.gleysonabreu.cardapio.R.menu.menu_app, menu);
        val item: MenuItem? = menu?.findItem(R.id.search);
        search_view.setMenuItem(item);


        return super.onCreateOptionsMenu(menu)
    }
}
