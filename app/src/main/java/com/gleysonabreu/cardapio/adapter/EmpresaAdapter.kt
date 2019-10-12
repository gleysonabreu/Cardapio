package com.gleysonabreu.cardapio.adapter

import android.content.Context
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView;
import com.gleysonabreu.cardapio.R
import com.gleysonabreu.cardapio.model.Empresa
import kotlinx.android.synthetic.main.empresas_items.view.*

class EmpresaAdapter (private val empresas: ArrayList<Empresa>,
                      private val context: Context,
                      private val itemClickListener: (View, Int, Int) -> Unit) : RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder>() {

    fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T{
        itemView.setOnClickListener {
            event.invoke(it, adapterPosition, getItemViewType());
        }
        return this;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.empresas_items, parent, false);
        var viewHolder = EmpresaViewHolder(view);
        viewHolder.onClick(itemClickListener);
        return viewHolder;

    }

    override fun getItemCount(): Int {
        return empresas.size;
    }

    override fun onBindViewHolder(holder: EmpresaViewHolder, position: Int) {
        holder.bindView(empresas[position]);
    }

    class EmpresaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nomeEmpresa = itemView.nomeEmpresa;
        val tipoComida = itemView.tipoComida;
        val tempoPreparo = itemView.tempoPreparo;
        val fotoEmpresa = itemView.fotoEmpresa;

        fun bindView(empresa: Empresa){
            nomeEmpresa.text = empresa.nome;
            tipoComida.text = empresa.tipo;
            tempoPreparo.text = empresa.tempo;
        }
    }
}