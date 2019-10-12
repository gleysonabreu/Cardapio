package com.gleysonabreu.cardapio.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gleysonabreu.cardapio.R
import com.gleysonabreu.cardapio.adapter.CardapioAdapter
import com.gleysonabreu.cardapio.model.Cardapio
import com.gleysonabreu.cardapio.model.Empresa
import kotlinx.android.synthetic.main.activity_cardapio.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

class CardapioActivity : AppCompatActivity() {

    private var listaCardapio: ArrayList<Cardapio> = ArrayList<Cardapio>();
    private lateinit var  linearLayoutManager: LinearLayoutManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardapio)

        linearLayoutManager = LinearLayoutManager(this);
        //Recovery data other activity
        var getDados = intent.getSerializableExtra("dadosEmpresa") as Empresa;
        if(getDados != null){
            toolbar.titleToolbar.text = getDados.nome;
        }
        // Toolbar settings
        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        //Lista de Cardapios statico;
        val cardapio: Cardapio = Cardapio();
        cardapio.nomeProduto = "Kit Maminha 1";
        cardapio.descricaoProduto = "300 de maminha + 1 porção de baião + 2 linguiças + 1 porção de batata e uma salada.";
        cardapio.precoProduto = 13.0;
        listaCardapio.add(cardapio);

        val cardapio2: Cardapio = Cardapio();
        cardapio2.nomeProduto = "Kit Maminha 2";
        cardapio2.descricaoProduto = "300 de maminha + 1 porção de baião + 2 linguiças + 1 porção de batata e uma salada.";
        cardapio2.precoProduto = 13.0;
        listaCardapio.add(cardapio2);

        val cardapio3: Cardapio = Cardapio();
        cardapio3.nomeProduto = "Kit Maminha 3";
        cardapio3.descricaoProduto = "300 de maminha + 1 porção de baião + 2 linguiças + 1 porção de batata e uma salada.";
        cardapio3.precoProduto = 13.0;
        listaCardapio.add(cardapio3);

        val cardapio4: Cardapio = Cardapio();
        cardapio4.nomeProduto = "Kit Maminha 4";
        cardapio4.descricaoProduto = "300 de maminha + 1 porção de baião + 2 linguiças + 1 porção de batata e uma salada.";
        cardapio4.precoProduto = 13.0;
        listaCardapio.add(cardapio4);

        val cardapio5: Cardapio = Cardapio();
        cardapio5.nomeProduto = "Kit Maminha 5";
        cardapio5.descricaoProduto = "300 de maminha + 1 porção de baião + 2 linguiças + 1 porção de batata e uma salada.";
        cardapio5.precoProduto = 13.0;
        listaCardapio.add(cardapio5);

        val cardapio6: Cardapio = Cardapio();
        cardapio6.nomeProduto = "Kit Maminha 6";
        cardapio6.descricaoProduto = "300 de maminha + 1 porção de baião + 2 linguiças + 1 porção de batata e uma salada.";
        cardapio6.precoProduto = 13.0;
        listaCardapio.add(cardapio6);

        val cardapio7: Cardapio = Cardapio();
        cardapio7.nomeProduto = "Kit Maminha 7";
        cardapio7.descricaoProduto = "300 de maminha + 1 porção de baião + 2 linguiças + 1 porção de batata e uma salada.";
        cardapio7.precoProduto = 13.0;
        listaCardapio.add(cardapio7);


        recyclerCardapio.adapter = CardapioAdapter(listaCardapio, this);
        recyclerCardapio.layoutManager = linearLayoutManager;

    }

    // Override method for appear new menu in the toolbar.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater;
        inflater.inflate(R.menu.menu_app, menu);
        val item: MenuItem? = menu?.findItem(R.id.search);
        search_view.setMenuItem(item);


        return super.onCreateOptionsMenu(menu)
    }
}
