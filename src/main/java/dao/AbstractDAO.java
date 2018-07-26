package dao;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import repository.IDBCP;

/**
 * すべてのDAOの親となるクラス
 */
@Slf4j
public abstract class AbstractDAO {

    @Inject
    protected IDBCP dbcp;

}
