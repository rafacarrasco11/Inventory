package com.example.inventory.ui.dependency;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.DependencyComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder>  {
    private ArrayList<Dependency> list;
    OnManagerDependencyListener listener;

    public interface OnManagerDependencyListener{

        // Pulsacion corta para editar
        void onEditDependency(Dependency dependency);
        // Pulsacion larga opara eliminar

        void onDeleteDependency(Dependency dependency);

        void onSelectDependency(Dependency dependency);
    }
    public DependencyAdapter(ArrayList<Dependency> list, OnManagerDependencyListener listener) {
        this.list = new ArrayList<Dependency>();
        this.listener = listener;
    }

    /**
     * Se llama a este metodo tantas veces como elelemtos se visualicen el la pantalla/ elementos
     * del array list, del dispositivo SIEMPRE y CUANDO getItemCount>0
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dependency_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .bold()
                .endConfig()
                .buildRound(list.get(position).getName().substring(0,1), ColorGenerator.DEFAULT.getRandomColor());

        holder.imgvIcon.setImageDrawable(drawable);

        holder.tvName.setText(list.get(position).getName());

        // Cuando se actualiza la lista, se indica a la clase Holder que dependencia es, y quien es su listener
        holder.bind(list.get(position), listener);

        // POR SI EN VEZ DE EL ITEM ENTERO, SOLO QUEREMOS QUE LA PULSACION SE APLIQUE A UN ELEMENTO DEL ITEM
        //holder.imgvIcon.setOnClickListener();

    }

    /**
     * Este método devuelve el numero de elementos del adapter. Y es utilizado por el sistema operativo
     * cuando se inicializa el componente recuclerview, si se deja a 0, NUNCA se muestran los
     * elementos del adapter en el RecyclerView, ya que no se llama al metodo onCreataViewHolder
     * @return
     */
    @Override
    public int getItemCount() {
        Log.d("elementos", String.valueOf(list.size()));
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView imgvIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            imgvIcon=itemView.findViewById(R.id.imgvIcon);

            //1º OPCION: SOLO QUIERO COMTROLAR EL EVENTO ON CLICK, COGE EL ITEM COMPLETO PARA LA PULSACION
            //Esto solo sirve para mostrar un mensaje genérico, ya que aqui no tenemos las posiciones
            //Luego esto no es posible porque necesitamos la posicion o la dependencia, solución: metodo bind
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnEditDependency();
                }
            });*/
        }

        /**
         * Todos los metodos que se crean en la clase ViewHolder tienen acceso al elemento
         * View que contiene la variable itemView, es decir la vista de cada elemento.
         * @param dependency
         * @param listener
         */
        public void bind(Dependency dependency, OnManagerDependencyListener listener) {
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onEditDependency(dependency);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteDependency(dependency);
                    //Devolviendo true se indica que se consume el evento, y se evita la propagacion del evento en otras vistas
                    return true;
                }
            });
        }


    }
    //region METODOS ACTUALIZA VISTA
    // se llama al metodo notifyDataChanged() para:
    //  - 1. Anular la vista
    //  - 2. Llama al metodo ondraw() de todos los elementos de la nueva vista

    public void updateList(List<Dependency> list) {
        this.list.clear();
        this.list.addAll(list);

        notifyDataSetChanged();
    }
    public void delete(Dependency deleted) {
        list.remove(deleted);
        notifyDataSetChanged();

     //   notifyItemRemoved(list.indexOf(deleted));
    }

    /**
     * Ordena la lista en base al metodo compareTo que se haya definido en la clase
     * modelo de la vist
     */
    public void order() {
        Collections.sort(list);
        notifyDataSetChanged();
    }

    public void inverseOrder() {
        Collections.reverse(list);
        //Collections.sort();

        notifyDataSetChanged();
    }

    /**
     * Ordenar la lista en base a un objeto que implemente la interfaz comparator
     */
    public void orderByDescription() {
        Collections.sort(list, new DependencyComparator());
        notifyDataSetChanged();
    }

    public void undo(Dependency deleted) {
        list.add(deleted);
        notifyDataSetChanged();
    }

    //endregion
}

