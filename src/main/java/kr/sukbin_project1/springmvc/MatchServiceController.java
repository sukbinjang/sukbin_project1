package kr.sukbin_project1.springmvc;

import kr.sukbin_project1.dao.MatchService;
import kr.sukbin_project1.dao.MatchServiceDao;
import kr.sukbin_project1.dao.Player;
import kr.sukbin_project1.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("matchServiceControllerV2")
@RequestMapping("/springmvc/v2/matchService")
public class MatchServiceController {

    private MatchServiceDao matchServiceDao;

    @Autowired
    public MatchServiceController(MatchServiceDao matchServiceDao) {
        this.matchServiceDao = matchServiceDao;
    }
    /**
     * 사용자가 매칭을 신청할 경우에 실행되는 메서드
     * 매칭을 신청하면 matchservice 테이블에 회원정보를 추가하고 티어별로 매칭을 진행하여 매칭을 관리
     */
    @PostMapping("/s/startMatching")
    public String startMatching(@SessionAttribute("PLAYER") Player player) throws Exception {
        MatchService matchService = new MatchService(player.getPlayerId(), player.getTier(), player.getRankingPoint());
        matchServiceDao.addPlayerMatchService(matchService);
        List<MatchService> matchServiceList = matchServiceDao.checkTier(matchService.getPlayerTier());
        if (matchServiceList.size() < 3) {
            matchServiceDao.updateStatus("wait", player.getPlayerId());
        } else {
            for (int i = 0; i < 4; i++) {
                matchServiceDao.updateStatus("match", matchServiceList.get(i).getPlayerId());
                matchServiceDao.deletePlayer(matchServiceList.get(i).getPlayerId());
            }
        }
        return "redirect:/app/springmvc/v2/player/playerStatus";
    }

}
