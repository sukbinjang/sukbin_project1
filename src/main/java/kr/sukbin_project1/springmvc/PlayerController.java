package kr.sukbin_project1.springmvc;


import kr.sukbin_project1.dao.Player;
import kr.sukbin_project1.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Servlet API를 사용하지 않는 UserController
 */
@Controller("playerControllerV2")
@RequestMapping("/springmvc/v2/player")
public class PlayerController {

    private final PlayerDao playerDao;

    @Autowired
    public PlayerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @GetMapping("/playerStatus")
    public void getStatus(@SessionAttribute("PLAYER") Player player,
                          Model model) {
        Player player1 = playerDao.getPlayer(player.getPlayerId());
        model.addAttribute("nowPlayer", player1);
    }

    /**
     * 사용자 목록 화면
     */
    @GetMapping("/playerList")
    public void playerList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int count,
            Model model) {
        int offset = (page - 1) * count;
        List<Player> playerList = playerDao.listPlayers(offset, count);
        int totalCount = playerDao.countPlayers();
        model.addAttribute("playerList", playerList);
        model.addAttribute("totalCount", totalCount);
    }

    /**
     * 플레이어 랭킹 화면
     */
    @GetMapping("/playerListRanked")
    public void playerListRanked(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int count,
            @RequestParam(required = false, defaultValue = "0") int tier,
            Model model) {
        int offset = (page - 1) * count;
        List<Player> rankedPlayerList = playerDao.listRankedPlayers(tier, offset, count);
        int totalCount = playerDao.countTierPlayers(tier);
        model.addAttribute("rankedPlayerList", rankedPlayerList);
        model.addAttribute("totalCount", totalCount);
    }

    /**
     * 사용자 정보 화면
     */
    @GetMapping("/playerInfo")
    public void playerInfo(@RequestParam(required = false) Integer playerId,
                           Model model) {
        model.addAttribute("player", playerDao.getPlayer(playerId));
    }

    /**
     * 사용자 등록 액션
     * html form에서 submit한 데이터가 @ModelAttribute 객체(Command Object)에 바인딩됨.
     */
    @PostMapping("/join")
    public String addPlayer(@ModelAttribute Player player,
                            RedirectAttributes attributes) {
        try {
            player.setTier(player.getPlayerLevel1(), player.getPlayerLevel2(), player.getPlayerLevel3());
            playerDao.addPlayer(player);

            return "redirect:/app/springmvc/v2/player/playerList";
        } catch (DuplicateKeyException e) {
            // redirect할때 attribute를 저장
            attributes.addFlashAttribute("msg", "Duplicate email");
            return "springmvc/v2/player/joinForm";
        }
    }

    /**
     * 로그인 액션
     */
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password,
                        @RequestParam(required = false, defaultValue = "/") String returnUrl,
                        HttpSession session, RedirectAttributes attributes) {
        try {
            Player player = playerDao.login(email, password);
            session.setAttribute("PLAYER", player);
            return "redirect:" + returnUrl; // 로그인 성공할 경우 returnUrl로
        } catch (EmptyResultDataAccessException e) { // 로그인 실패할 경우
            attributes.addFlashAttribute("email", email);
            attributes.addFlashAttribute("msg", "Wrong email or password");
            return "redirect:/app/springmvc/v2/player/loginForm?returnUrl=" +
                    URLEncoder.encode(returnUrl, Charset.defaultCharset());
        }
    }

    /**
     * 개인정보 수정 액션
     */
    @PostMapping("/s/updatePlayer")
    public String updatePlayer(Player player,
                               @SessionAttribute("PLAYER") Player sessionPlayer,
                               RedirectAttributes attributes) {
        try {
            player.setPlayerId(sessionPlayer.getPlayerId());
            playerDao.updatePlayer(player);
            // 개인정보 수정 후에 세션 업데이트
            sessionPlayer.setEmail(player.getEmail());
            sessionPlayer.setName(player.getName());
            return "springmvc/v2/player/s/myInfo";
        } catch (DuplicateKeyException e) {
            attributes.addFlashAttribute("player", player);
            attributes.addFlashAttribute("msg", "Duplicate email");
            return "redirect:/app/springmvc/v2/player/s/playerEdit";
        }
    }

    /**
     * 비밀번호 수정 액션
     */
    @PostMapping("/s/updatePassword")
    public String updatePassword(String password, String newPassword,
                                 @SessionAttribute("PLAYER") Player player, RedirectAttributes attributes) {
        int result =
                playerDao.updatePassword(player.getPlayerId(), password, newPassword);
        if (result > 0) {
            return "springmvc/v2/player/s/myInfo";
        } else {
            attributes.addFlashAttribute("msg", "Wrong password");
            return "redirect:/app/springmvc/v2/player/s/passwordForm";
        }
    }

    /**
     * 로그 아웃
     */
    @GetMapping("/s/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}