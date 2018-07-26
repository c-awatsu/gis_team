package answer.dao;

import answer.Bean.ContributionBean;
import org.sql2o.Connection;
import java.sql.SQLException;
import java.util.List;

public class ContributionDAO extends AbstractDAO implements IContributionDAO {
    @Override
    public List<ContributionBean> selectContributionList() {
        List<ContributionBean> contributionBeanList = null;
        String sql = "select latlon, post_time from contribution where available = 'true'";
        try(Connection conn = dbcp.getSql2o().open()){
            contributionBeanList = conn.createQuery(sql)
                    .setAutoDeriveColumnNames(true)//Beanの変数名とDBのカラム名をキャメルケースとスネークケースの差を吸収してマッピングしてくれる
                    .executeAndFetch(ContributionBean.class);//基本データ型(int, floatとか)以外の任意の型のListでsqlの結果が返ってくる時はexecuteAndFetch(型名.class)を使う
                                                             //他にもいろいろあるのでsql2oでググって調べてみてください
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contributionBeanList;
    }
}
