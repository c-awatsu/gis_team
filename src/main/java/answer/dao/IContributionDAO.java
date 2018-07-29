package answer.dao;

import answer.Bean.ContributionBean;
import com.google.inject.ImplementedBy;

import java.util.List;

//とりあえずおまじない
//気になる人はgoogle juiceとかDIコンテナとかでググってください
@ImplementedBy(ContributionDAO.class)
public interface IContributionDAO {
    List<ContributionBean> selectContributionList();
}
