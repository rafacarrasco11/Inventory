package com.example.inventory.ui.section;

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
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.dependency.DependencyAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder>{
    private List<Section> list;
    SectionAdapter.OnManagerSectionListener listener;

    interface OnManagerSectionListener{
        void onEditSection(Section section);

        void onDeleteSection(Section section);
    }

    public SectionAdapter(ArrayList<Section> list, SectionAdapter.OnManagerSectionListener listener) {
        this.list = new ArrayList<Section>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .toUpperCase()
                .bold()
                .endConfig()
                .buildRound(list.get(position).getName().substring(0,1), ColorGenerator.DEFAULT.getRandomColor());

        holder.imageIcon.setImageDrawable(drawable);

        holder.tvName.setText(list.get(position).getName());

        // Cuando se actualiza la lista, se indica a la clase Holder que dependencia es, y quien es su listener
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imageIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameSection) ;
            imageIcon = itemView.findViewById(R.id.imgIconSection) ;
        }

        public void bind(Section section, OnManagerSectionListener listener) {
            itemView.setOnLongClickListener(v -> {
                listener.onDeleteSection(section);
                return true;
            });

            itemView.setOnClickListener(v -> {
                listener.onEditSection(section);
            });
        }
    }

    //region METODOS ACTUALIZA VISTA

    public void updateList(List<Section> list) {
        this.list.clear();
        this.list.addAll(list);

        notifyDataSetChanged();
    }
    public void delete(Section sectionDeleted) {
        list.remove(sectionDeleted);
        notifyDataSetChanged();

    }

    /**
     * Ordena la lista en base al metodo compareTo que se haya definido en la clase
     * modelo de la vist
     */
    public void order() {
        Collections.sort(list);
        notifyDataSetChanged();
    }


    public void undo(Section sectionDeleted) {
        list.add(sectionDeleted);
        notifyDataSetChanged();
    }

    //endregion
}
