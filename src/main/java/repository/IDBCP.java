package repository;

import com.google.inject.ImplementedBy;
import org.sql2o.Sql2o;

import java.sql.SQLException;

@ImplementedBy(DBCP.class)
public interface IDBCP {

    /**
     * DB接続用のSql2oを取得する.
     *
     * @return {@link Sql2o}
     * @throws SQLException コネクションが確保できなかったとき
     */
    Sql2o getSql2o() throws SQLException;

}