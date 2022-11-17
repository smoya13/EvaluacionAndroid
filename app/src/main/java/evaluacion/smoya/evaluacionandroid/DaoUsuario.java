package evaluacion.smoya.evaluacionandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface DaoUsuario {
    // Seleccionando todos los datos de usuarios
    @Query("SELECT * FROM usuario")
    List<Usuario> obtenerUsuarios();

    // Seleccionando todos los ids de usuario
    @Query("SELECT * FROM usuario WHERE uid IN (:userIds)")
    List<Usuario> obtenerPorId(int[] userIds);

    // Insertando los usuarios - C
    @Insert
    void insertarUsuario(Usuario... usuarios);

    // Eliminando un usuario en especifico - R
    @Delete
    void eliminar(Usuario usuario);

    // Actualizando un usuario en especifico - U
    @Update
    void actualizar(Usuario usuario);

    // Eliminando - D
    @Query("DELETE FROM usuario")
    void deleteAllCourses();

}
