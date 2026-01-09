package dao;

import java.util.List;
import modelo.ModeloU;

public interface DAOGeneral2<K,E> {
public boolean agregar(ModeloU elemento);
public List<E> consultar();
public boolean actualizar(K id, E nuevo);
public boolean eliminar(K id);
}
