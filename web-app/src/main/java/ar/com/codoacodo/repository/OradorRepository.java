package ar.com.codoacodo.repository;

  //java collection framework: en java avanzado
  import java.util.List;

  import ar.com.codoacodo.entity.Orador;

  public interface OradorRepository {
    public void save(Orador orador);
    public Orador getById(Long id);
    public void update(Orador orador);
    public void delete(Long id);
    public List<Orador> findAll();
  }
