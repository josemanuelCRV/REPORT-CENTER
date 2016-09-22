package com.labs.josemanuel.reportcenter.Repository;

import java.util.List;

/**
 * Created by Miguel on 22-09-16.
 */
interface DBHelper {

    List<ProposalDTOout> getProposals();

    int insertProposal(ProposalVOin proposalVOin);
}
