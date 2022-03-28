package kr.sukbin_project1.springmvc;

import kr.sukbin_project1.HttpUtils;
import kr.sukbin_project1.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("ScoreBoardControllerv2")
@RequestMapping("springmvc/v2/scoreBoard")
@Slf4j
public class ScoreBoardController {
    private ScoreBoardDao scoreBoardDao;
    private PlayerDao playerDao;

    @Autowired
    public ScoreBoardController(ScoreBoardDao scoreBoardDao, PlayerDao playerDao) {
        this.scoreBoardDao = scoreBoardDao;
        this.playerDao = playerDao;
    }
    /**
     * 사용자의 전적을 보여주는 메서드
     */
    @GetMapping("/scoreList")
    public String scoreList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int count,
            @RequestParam int playerId,
            HttpServletRequest request,
            Model model) {
        int offset = (page - 1) * count;
        List<ScoreBoard> scoreBoardList =
                scoreBoardDao.listMatches(playerId, offset, count);
        int totalCount = scoreBoardDao.countMatches(playerId);
        model.addAttribute("scoreBoardList", scoreBoardList);
        model.addAttribute("totalCount", totalCount);
        log.debug(HttpUtils.getRequestURLWithQueryString(request));
        // 현재 리스트 페이지를 세션에 넣는다.
        request.getSession().setAttribute("listPage",
                HttpUtils.getRequestURLWithQueryString(request));
        return "springmvc/v2/scoreBoard/scoreBoardList";
    }
    /**
     * 경기를 기록할 때 사용되는 메서드로 경기내용에 따라서 랭킹포인트를 산정하여 플레이어 정보를 업데이트
     */
    @GetMapping("/s/addMatch")
    public String addMatch(@ModelAttribute ScoreBoard scoreBoard,
                           @SessionAttribute("PLAYER") Player sessionplayer) {
        // 세션의 player로 사용자 정보를 설정한다.
        scoreBoard.setPlayerId(sessionplayer.getPlayerId());
        scoreBoardDao.addMatch(scoreBoard);
        System.out.println(scoreBoard);
        Player player = playerDao.getPlayer(sessionplayer.getPlayerId());
        //스코어
        int point = 20 + Math.abs(Integer.parseInt(scoreBoard.getScore1()) - Integer.parseInt(scoreBoard.getScore2()));
        System.out.println(scoreBoard.getResult());
        //승리한 경우
        if (scoreBoard.getResult().equals("w")) {
            System.out.println("1");
            if (player.getTier() == 0) player.setRankingPoint(player.getRankingPoint() + point);
            else {
                if (player.getRankingPoint() + point >= 100){
                    player.setTier(player.getTier() - 1);
                    player.setRankingPoint(20);
                }
                else{
                    player.setRankingPoint(player.getRankingPoint() + point);
                }

            }

        }
        //패배한 경우
        else{
            System.out.println("2");
            if(player.getTier() == 5){
                player.setRankingPoint(player.getRankingPoint() - point);
            }
            else{
                if(player.getRankingPoint() - point < 0){
                    player.setTier(player.getTier() + 1);
                    player.setRankingPoint(50);
                }
                else{
                    player.setRankingPoint(player.getRankingPoint() - point);
                }

            }
        }

        playerDao.setStatus(sessionplayer);
        playerDao.setRankPoint(player);
        // 플레이어의 매칭상태 페이지로 돌아감
        return "redirect:/app/springmvc/v2/player/playerStatus";
    }

}
