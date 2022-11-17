package evaluacion.smoya.evaluacionandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class}, version = 1)
public abstract class BDApp extends RoomDatabase {
    public abstract DaoUsuario daoUsuario();
}
