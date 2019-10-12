package com.gleysonabreu.cardapio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gleysonabreu.cardapio.model.Cardapio
import androidx.recyclerview.widget.RecyclerView;
import com.gleysonabreu.cardapio.R
import kotlinx.android.synthetic.main.cardapio_items.view.*
import kotlinx.android.synthetic.main.empresas_items.view.*

class CardapioAdapter (private val cardapio: ArrayList<Cardapio>, private val context: Context) : RecyclerView.Adapter<CardapioAdapter.CardapioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardapioViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.cardapio_items, parent, false);
        return CardapioViewHolder(view);

    }

    override fun getItemCount(): Int {
        return cardapio.size;
    }

    override fun onBindViewHolder(holder: CardapioViewHolder, position: Int) {
        holder.bindItems(cardapio[position]);
    }


    class CardapioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nomeProduto = itemView.nomeProdutoCardapio;
        val descricaoProduto = itemView.descricaoProdutoCardapio;
        val fotoProduto = itemView.fotoProdutoCardapio;
        val precoProduto = itemView.precoProdutoCardapio;

        fun bindItems(cardapio: Cardapio){

            nomeProduto.text = cardapio.nomeProduto;
            descricaoProduto.text = cardapio.descricaoProduto;
            precoProduto.text = "R$ " + cardapio.precoProduto;

        }
    }
}