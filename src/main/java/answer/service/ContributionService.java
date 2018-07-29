package answer.service;

import answer.Bean.ContributionBean;
import answer.dao.IContributionDAO;
import com.google.inject.Inject;

import java.util.List;


public class ContributionService implements IContributionService{
    @Inject
    private IContributionDAO contributionDAO;

    @Override
    public List<ContributionBean> selectContributionList() {
        return contributionDAO.selectContributionList();
    }
}
