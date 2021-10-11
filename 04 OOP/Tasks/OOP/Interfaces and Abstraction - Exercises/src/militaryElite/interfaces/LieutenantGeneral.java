package militaryElite.interfaces;

import militaryElite.entities.PrivateImpl;

import java.util.List;

public interface LieutenantGeneral {

     void addPrivate(PrivateImpl priv);
     List<PrivateImpl> getPrivates();
}
