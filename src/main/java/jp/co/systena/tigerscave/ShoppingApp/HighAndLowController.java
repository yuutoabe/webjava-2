package jp.co.systena.tigerscave.ShoppingApp;

import jp.co.systena.tigerscave.ShoppingApp.HighAndLow.HighAndLow;
import jp.co.systena.tigerscave.ShoppingApp.HighAndLow.HighAndLowSession;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HighAndLowController {

    @Autowired
    private HighAndLowSession session;

    @RequestMapping("/highlow")
    public ModelAndView startGame(ModelAndView modelAndView) {
        val game = new HighAndLow();
        game.initGame();
        session.setHighAndLow(game);
        modelAndView.addObject("card", ImagePathUtility.getCardImagePathList(game.getCardList()));
        modelAndView.setViewName("HighLowView");
        return modelAndView;
    }

    @RequestMapping("/high")
    public ModelAndView high(ModelAndView modelAndView) {
        val game = session.getHighAndLow();
        if (game.executeGame(true)) {
            modelAndView.addObject("card", ImagePathUtility.getCardImagePathList(game.getCardList()));
            modelAndView.setViewName("HighLowView");
        } else {
            modelAndView.addObject("result", game.endGame());
            modelAndView.addObject("card", ImagePathUtility.getCardImagePathList(game.getCardList()));
            modelAndView.setViewName("HighLowResultView");
        }
        return modelAndView;

    }

    @RequestMapping("/low")
    public ModelAndView low(ModelAndView modelAndView) {
        val game = session.getHighAndLow();
        if (game.executeGame(false)) {
            modelAndView.addObject("card", ImagePathUtility.getCardImagePathList(game.getCardList()));
            modelAndView.setViewName("HighLowView");
        } else {
            modelAndView.addObject("result", game.endGame());
            modelAndView.addObject("card", ImagePathUtility.getCardImagePathList(game.getCardList()));
            modelAndView.setViewName("HighLowResultView");
        }
        return modelAndView;
    }

}
