package tn.esprit.spring.services.bloc;

import java.util.List;

import tn.esprit.spring.dao.entities.Bloc;

public interface IBlocService {
    Bloc addOrUpdate(Bloc b);
    Bloc addOrUpdate2(Bloc b);

    List<Bloc> findAll();

    Bloc findById(long id);

    void deleteById(long id);

    void delete(Bloc b);

    Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc);
    Bloc affecterBlocAFoyer( String nomBloc,  String nomFoyer) ;


}
