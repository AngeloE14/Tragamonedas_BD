package dao;

import java.util.List;
import modelo.Modelo;

public interface DAOGeneral<K,E> {
public boolean agregar(Modelo elemento);
public List<E> consultar();
public boolean actualizar(K id, E nuevo);
public boolean eliminar(K id);
}
