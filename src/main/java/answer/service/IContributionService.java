package answer.service;

import answer.Bean.ContributionBean;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(ContributionService.class)
public interface IContributionService {
    List<ContributionBean> selectContributionList();
}
