package militaryElite.Interfaces;

import militaryElite.entities.PrivateImpl;

import java.util.List;

public interface LieutenantGeneral  extends Private{
    void addPrivate(PrivateImpl priv);
    List<PrivateImpl> getPrivates();

}
