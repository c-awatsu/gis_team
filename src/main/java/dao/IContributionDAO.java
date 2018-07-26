package dao;

import Bean.ContributionBean;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(ContributionDAO.class)
public interface IContributionDAO {
    List<ContributionBean> selectContributionList();
}
