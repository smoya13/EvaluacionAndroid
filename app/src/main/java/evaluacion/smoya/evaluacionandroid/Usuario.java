package evaluacion.smoya.evaluacionandroid;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario
{
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "user")
    public String user;

    @ColumnInfo(name = "pass")
    public String pass;

    @ColumnInfo(name = "genero")
    public String genero;

    public Usuario(int uid, String user, String pass, String genero) {
        this.uid = uid;
        this.user = user;
        this.pass = pass;
        this.genero = genero;
    }

    public Usuario() {

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
