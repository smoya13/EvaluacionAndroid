package evaluacion.smoya.evaluacionandroid;

import android.app.Person;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Aqui van los elementos a mostrar en el RecyclerView
public class AdaptadorServicios extends RecyclerView.Adapter<AdaptadorServicios.ViewHolderServicios> {

    // Genero la lista de ServicioVo
    ArrayList<ServicioVo> listaServicios;

    // Constructor de lista servicios para llamarla más adelante
    public AdaptadorServicios(ArrayList<ServicioVo> listaServicios) {
        this.listaServicios = listaServicios;
    }
    @NonNull
    @Override
    public ViewHolderServicios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false); // Llamo al layout item_list_view
        return new ViewHolderServicios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderServicios holder, int position) {
        holder.txtTitulo.setText(listaServicios.get(position).getNombre());
        holder.txtDescripcion.setText(listaServicios.get(position).getInfo());
        holder.foto.setImageResource(listaServicios.get(position).getFoto());
        holder.ratingBar.setRating(listaServicios.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public class ViewHolderServicios extends RecyclerView.ViewHolder {
        // Variables
        TextView txtTitulo, txtDescripcion;
        ImageView foto;
        RatingBar ratingBar;

        public ViewHolderServicios(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            foto = itemView.findViewById(R.id.imgItem);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
