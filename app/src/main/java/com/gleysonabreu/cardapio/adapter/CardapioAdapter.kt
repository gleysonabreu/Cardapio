package com.gleysonabreu.cardapio.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gleysonabreu.cardapio.model.Cardapio
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide
import com.gleysonabreu.cardapio.R
import com.gleysonabreu.cardatronicoempresa.helper.Base64Custom
import kotlinx.android.synthetic.main.cardapio_items.view.*
import kotlinx.android.synthetic.main.empresas_items.view.*
import java.math.BigDecimal

class CardapioAdapter (private val cardapio: ArrayList<Cardapio>, private val context: Context) : RecyclerView.Adapter<CardapioAdapter.CardapioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardapioViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.cardapio_items, parent, false);
        return CardapioViewHolder(view);

    }

    override fun getItemCount(): Int {
        return cardapio.size;
    }

    override fun onBindViewHolder(holder: CardapioViewHolder, position: Int) {
        holder.bindItems(cardapio[position], context);
    }


    class CardapioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(cardapio: Cardapio, context: Context){

            if( cardapio.photoItem != null ){
                var url: Uri = Uri.parse(cardapio.photoItem);
                Glide.with(context).load(url).into(itemView.fotoProdutoCardapio)
            }
            itemView.nomeProdutoCardapio.text = cardapio.nameitem;
            itemView.textCategory.text = Base64Custom.decodificarBase64(cardapio.idCategory);
            itemView.descricaoProdutoCardapio.text = cardapio.descriptionItem;
            itemView.precoProdutoCardapio.text = "R$ " + cardapio.priceitem.toString();

            if( cardapio.discountPorcentItem > 0 && cardapio.discountPorcentItem <= 99 ){

                itemView.textPriceWithDiscount.visibility = View.VISIBLE;
                itemView.precoProdutoCardapio.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG;
                var result = cardapio.priceitem - (( cardapio.discountPorcentItem / 100.0 ) * cardapio.priceitem);
                itemView.textPriceWithDiscount.text = "R$ " + result.toString();

            }else if (cardapio.discountPorcentItem >= 100){
                itemView.textPriceWithDiscount.visibility = View.VISIBLE;
                itemView.precoProdutoCardapio.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG;
                itemView.textPriceWithDiscount.text = "Grátis";
            }else{
                itemView.textPriceWithDiscount.visibility = View.GONE;
                itemView.precoProdutoCardapio.paintFlags = 0;
            }


        }
    }
}