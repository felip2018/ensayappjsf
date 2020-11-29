package com.adsi.ensayapp.ejb;

import com.adsi.ensayapp.model.Recargo;
import java.util.List;

public interface RecargoFacadeLocal {
    void create(Recargo recargo);

    void edit(Recargo recargo);

    void remove(Recargo recargo);

    Recargo find(Object id);

    List<Recargo> findAll();

    List<Recargo> findRange(int[] range);

    int count();
    
    List<Object[]> getAllCharges();
}
